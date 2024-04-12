package com.example.eattolife.sql;

import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlHelp {
    public static int getFoodSize() {
        final String CLS = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://10.66.178.144:3306/eattolife";
        final String USER = "pxj";
        final String PWD = "123456";

        int count = 0;

        try {
            Class.forName(CLS);  
            Connection conn = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select count(1) as res from haixin";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { //while逐条循环查询
                count = rs.getInt("res");
            }
            //Log.e("查询所得数量为", String.valueOf(count));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return count;
    }
}
