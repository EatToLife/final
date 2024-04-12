package com.example.eattolife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class YunDongSJK extends SQLiteOpenHelper {

    private static final String DB_NAME = "shiwu.db";
    private static final String TABLE_NAME = "shiwu_info";
    private static final int DB_VERSION = 1;
    private static YunDongSJK mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;


    public YunDongSJK(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // 利用单例模式获取数据库帮助器的唯一实例
    private static YunDongSJK getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new YunDongSJK(context);
        }
        return mHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getReadableDatabase();
        }
        return mWDB;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }

        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    // 创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " KaLuL INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    // 更新数据库版本
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
