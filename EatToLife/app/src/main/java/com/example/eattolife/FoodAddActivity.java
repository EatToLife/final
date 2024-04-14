package com.example.eattolife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eattolife.basic.Wo;
import com.example.eattolife.basic.YinShiTJ;
import com.example.eattolife.tools.CommonUtils;

public class FoodAddActivity extends AppCompatActivity {
    private EditText foodName, foodPrice, foodCalorie;

    private FoodInfo foodInfo;
    private FoodDao foodDao; //用户数据操作类实例
    private Handler mainHandler;

    /**
     * 自定义添加饮食的业务代码
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_add);

        foodName = findViewById(R.id.foodName);
        foodPrice = findViewById(R.id.foodPrice);
        foodCalorie = findViewById(R.id.foodCalorie);

        foodInfo = new FoodInfo();
        foodDao = new FoodDao();
        mainHandler = new Handler(getMainLooper());

        //返回跳转按钮
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FoodAddActivity.this, YinShiTJ.class);
                startActivity(intent);
            }
        });
    }

    //确定（保存）的点击事件处理
    public void btn_ok_click(View v) {
        final String _foodName = foodName.getText().toString().trim(); //trim移除空白字符
        final String _foodPrice = foodPrice.getText().toString().trim();
        final String _foodCalorie = foodCalorie.getText().toString().trim();
        //float foodCalorieFloat = Float.parseFloat(foodCalorie);

        if (TextUtils.isEmpty(_foodName)) {
            CommonUtils.showShortMsg(this, "请输入饮食日期！");
            foodName.requestFocus(); //获取焦点
        } else if (TextUtils.isEmpty(_foodPrice)) {
            CommonUtils.showShortMsg(this, "请输入餐别！");
            foodPrice.requestFocus();
        } else if (TextUtils.isEmpty(_foodCalorie)) {
            CommonUtils.showShortMsg(this, "请输入热量！");
            foodCalorie.requestFocus();
        } else {
            final FoodInfo item = new FoodInfo();
            item.setFoodName(_foodName);
            item.setFoodPrice(_foodPrice);
            item.setFoodCalorie(Float.parseFloat(_foodCalorie));
            new Thread(new Runnable() {
                final int iRow = foodDao.addFoodInfo(item);
                @Override
                public void run() {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setResult(1); //使用参数表示当前界面操作成功，并返回饮食推荐界面
                            finish();
                        }
                    });
                }
            }).start();
        }
    }
}