package com.example.eattolife;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity {

    private static final int[] DATA_SIZES = {100000, 500000, 1000000}; // 测试数据量
    private static final int[] THREAD_COUNTS = {1, 2, 4, 8}; // 测试线程数量
    private static final int TEST_COUNT = 5; // 每组测试的次数

    //数据库连接
    private static final String URL = "jdbc:mysql://10.66.178.144:3306/eattolife";
    private static final String USER = "pxj";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        try {
            // 加载 MySQL 驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 生成测试数据文件
            generateTestDataFile("data.txt", 1000000);

            // 执行测试并生成报告
            generateReport();
            System.out.println("测试完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 生成测试数据文件
    private static void generateTestDataFile(String filename, int size) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < size; i++) {
            writer.write(Integer.toString((int) (Math.random() * size)));
            writer.newLine();
        }
        writer.close();
    }

    public static void generateReport() throws SQLException, InterruptedException, IOException {
        BufferedWriter reportWriter = new BufferedWriter(new FileWriter("report.txt"));

        // 执行测试
        for (int dataSize : DATA_SIZES) {
            for (int threadCount : THREAD_COUNTS) {
                long totalTime1 = 0, totalTime2 = 0;
                reportWriter.write("数据量：" + dataSize + "，线程数量：" + threadCount + "，\n");

                for (int i = 0; i < TEST_COUNT; i++) {
                    long time1 = testStrategy("平均分块", dataSize, threadCount);
                    totalTime1 += time1;
                    long time2 = testStrategy("动态分块", dataSize, threadCount);
                    totalTime2 += time2;
                    reportWriter.write("  测试" + (i + 1) + "：平均分块耗时：" + time1 + "ms，动态分块耗时：" + time2 + "ms\n");
                }

                double avgTime1 = totalTime1 / (double) TEST_COUNT;
                double avgTime2 = totalTime2 / (double) TEST_COUNT;

                reportWriter.write("  平均耗时：平均分块：" + avgTime1 + "ms，动态分块：" + avgTime2 + "ms\n");
                double speedup = avgTime1 / avgTime2;
                reportWriter.write("  加速比：" + speedup + " 倍\n\n");
            }
        }

        reportWriter.close();
    }

    public static long testStrategy(String strategy, int dataSize, int threadCount) throws SQLException, InterruptedException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        long startTime = System.currentTimeMillis();

        // 获取数据总量
        int totalRows = getCount(conn);

        // 分块策略
        List<List<Integer>> subLists;
        if (strategy.equals("平均分块")) {
            subLists = splitDataEqually(totalRows, threadCount);
        } else {
            subLists = splitDataDynamically(totalRows, threadCount);
        }

        // 创建多个线程进行并行排序
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (List<Integer> subList : subLists) {
            new Thread(new SortTask(conn, subList, latch)).start();
        }

        // 等待所有线程排序完成
        latch.await();

        long endTime = System.currentTimeMillis();
        conn.close();
        return endTime - startTime;
    }

    // 获取数据总量
    private static int getCount(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM foodinfo";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        rs.close();
        stmt.close();
        return count;
    }

    // 平均分块策略
    private static List<List<Integer>> splitDataEqually(int totalRows, int threadCount) {
        List<List<Integer>> subLists = new ArrayList<>();
        int chunkSize = totalRows / threadCount;
        int startRow = 0;
        for (int i = 0; i < threadCount - 1; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < chunkSize; j++) {
                subList.add(startRow + j);
            }
            subLists.add(subList);
            startRow += chunkSize;
        }
        // 添加最后一个子列表，确保所有数据都被分配到线程中
        List<Integer> subList = new ArrayList<>();
        for (int i = startRow; i < totalRows; i++) {
            subList.add(i);
        }
        subLists.add(subList);
        return subLists;
    }

    // 动态分块策略
    private static List<List<Integer>> splitDataDynamically(int totalRows, int threadCount) {
        List<List<Integer>> subLists = new ArrayList<>();
        int chunkSize = totalRows / threadCount;
        int startRow = 0;
        for (int i = 0; i < threadCount - 1; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < chunkSize; j++) {
                subList.add(startRow + j);
            }
            subLists.add(subList);
            startRow += chunkSize;
        }
        // 添加最后一个子列表，并根据剩余数据动态调整大小
        List<Integer> subList = new ArrayList<>();
        for (int i = startRow; i < totalRows; i++) {
            subList.add(i);
        }
        subLists.add(subList);

        // 动态调整子列表大小
        int averageSize = totalRows / threadCount;
        int remainder = totalRows % threadCount;
        for (int i = 0; i < remainder; i++) {
            subLists.get(i).add(startRow + i);
        }

        return subLists;
    }

    // 排序任务
    private static class SortTask implements Runnable {
        private Connection conn;
        private List<Integer> indexes;
        private CountDownLatch latch;

        public SortTask(Connection conn, List<Integer> indexes, CountDownLatch latch) {
            this.conn = conn;
            this.indexes = indexes;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // 从数据库中读取数据并进行排序
                List<Integer> data = fetchData(conn);

                // 使用快速排序进行排序
                Collections.sort(data);

                // 输出排序后的数据（此处仅输出前10个元素）
                System.out.println(Thread.currentThread().getName() + " 排序结果：" + data.subList(0, Math.min(10, data.size())));

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                latch.countDown();
            }
        }

        // 从数据库中获取数据
// 从数据库中获取数据
        private List<Integer> fetchData(Connection conn) throws SQLException {
            List<Integer> data = new ArrayList<>();
            String sql = "SELECT foodCalorie FROM foodinfo";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                data.add(rs.getInt("foodCalorie"));
            }
            rs.close();
            stmt.close();
            return data;
        }

    }
}
