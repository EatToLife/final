package com.example.eattolife.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BreakfastHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "eating.db";
    //餐厅信息表
    private static final String TABLE_CANTEEN_INFO = "canteen_info";
    //食品信息表
    private static final String TABLE_FOOD_INFO = "food_info";

    //构造函数
    private Context mContext;
    public BreakfastHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //创建数据库，执行建表语句
    public void onCreate(SQLiteDatabase db) {
        //创建食品信息表
        String sql = "CREATE TABLE TABLE_FOOD_INFO" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + //下划线id：保证sql的可移植性【SQLite、Oracle、SQL server】
//                "name VARCHAR NOT NULL," +
//                "calorie FLOAT NOT NULL," +
//                "price FLOAT NOT NULL," +
                "pic_path VARCHAR NOT NULL);"; //商品图片的路径
        db.execSQL(sql);
        Toast.makeText(mContext, "Created succeed", Toast.LENGTH_SHORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
