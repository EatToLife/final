package com.example.eattolife;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.eattolife.basic.Wo;
import com.example.eattolife.basic.YinShiTJ;
import com.example.eattolife.sql.DbOpenHelper;
import com.example.eattolife.tools.CommonUtils;

import java.util.List;

public class AddFoodRecord extends AppCompatActivity implements View.OnClickListener{
    private Button btn_save, btn_cancel; //用户的饮食信息保存 & 取消按钮
    private EditText et_foodDate, et_foodMeal, et_foodCalorie; //饮食日期，饮食餐别，饮食热量

    private FoodDao foodDao; //用户自定义添加食物的数据库操作类
    private DbOpenHelper dbOpenHelper; //数据库连接辅助类
    private Handler mainHandler; //主线程

    public AddFoodRecord() {
        // 这是默认的无参数构造函数，可以不做任何事情，或者根据需要初始化一些变量
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_record);

        initView(); //初始化

    }


    private void initView() {
        btn_save = findViewById(R.id.btn_save);  //保存饮食信息的按钮
        btn_cancel = findViewById(R.id.btn_cancel);  //返回主界面home

        et_foodDate = findViewById(R.id.et_foodDate);
        et_foodMeal= findViewById(R.id.et_foodMeal);
        et_foodCalorie = findViewById(R.id.et_foodCalorie);

        foodDao = new FoodDao(); //数据库操作类
        dbOpenHelper = new DbOpenHelper();
        mainHandler = new Handler(getMainLooper()); //获取主线程

        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            Intent intent = new Intent();
            intent.setClass(AddFoodRecord.this, Wo.class); //从Wo跳转到AddFoodRecord
            startActivity(intent);
        } else if (v.getId() == R.id.btn_save) {
            doQueryFoodRecord();
        }
    }

    private void doQueryFoodRecord() {
        final String foodDate = et_foodDate.getText().toString().trim(); //trim移除空白字符
        final String foodMeal = et_foodMeal.getText().toString().trim();
        final String foodCalorie = et_foodCalorie.getText().toString().trim();
        //float foodCalorieFloat = Float.parseFloat(foodCalorie);

        if (TextUtils.isEmpty(foodDate)) {
            CommonUtils.showShortMsg(this, "请输入饮食日期");
            et_foodDate.requestFocus(); //获取焦点
        } else if (TextUtils.isEmpty(foodMeal)) {
            CommonUtils.showShortMsg(this, "请输入餐别");
            et_foodDate.requestFocus();
        } else if (TextUtils.isEmpty(foodCalorie)) {
            CommonUtils.showShortMsg(this, "请输入热量");
            et_foodDate.requestFocus();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final FoodRecord item = foodDao.getFoodRecordByfoodDateAndfoodMeal(foodDate, foodMeal); //内部类访问外部类（外部类需为常量）

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (item == null) {
                                CommonUtils.showDlgMsg(AddFoodRecord.this, "饮食信息有误");
                            } else {
                                CommonUtils.showDlgMsg(AddFoodRecord.this, "保存成功，进入饮食信息管理界面");
                                //调用用户管理界面
                                Intent intent = new Intent(AddFoodRecord.this, YinShiTJ.class);
                                startActivity(intent);
                                //finish(); //必须结束当前界面
                            }
                        }
                    });
                }
            }).start();
        }
    }

}