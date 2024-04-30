//创建线程
public class ThreadSort implements Runnable{
    //run方法线程体
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("我在排序--"+i);
        }
    }

    //创建用户线程（主线程）
    public static void main(String[] args) {
        //创建runnable接口的实现类对象
        ThreadSort threadSort = new ThreadSort();

        //创建线程对象来开启线程 （代理）
        new Thread(threadSort).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("我在实现多线程--"+i);
        }
    }
}
