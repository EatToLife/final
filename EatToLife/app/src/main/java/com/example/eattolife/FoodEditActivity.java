package com.example.eattolife;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eattolife.basic.Wo;
import com.example.eattolife.basic.YinShiTJ;
import com.example.eattolife.tools.CommonUtils;

public class FoodEditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText foodName, foodPrice, foodCalorie;
    private TextView tv_creatDt;

    private Button btn_cancel_click, btn_ok_click;

    private FoodInfo foodInfoEdit; //当前要修改的饮食信息
    private FoodDao foodDao; //用户数据操作类实例
    private Handler mainHandler;

    /**
     * 自定义添加饮食的业务代码
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_edit);

        foodName = findViewById(R.id.foodName);
        foodPrice = findViewById(R.id.foodPrice);
        foodCalorie = findViewById(R.id.foodCalorie);

        tv_creatDt = findViewById(R.id.tv_creatDt);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            foodInfoEdit = (FoodInfo) bundle.getSerializable("foodFoodRdit");

            foodName.setText(foodInfoEdit.getFoodName());
            foodPrice.setText(foodInfoEdit.getFoodPrice());
            foodCalorie.setText((int) foodInfoEdit.getFoodCalorie());
        }

        foodDao = new FoodDao();
        mainHandler = new Handler(getMainLooper());


        btn_cancel_click = findViewById(R.id.btn_cancel_click);
        btn_ok_click = findViewById(R.id.btn_ok_click);

        btn_cancel_click.setOnClickListener(this);
        btn_ok_click.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok_click) {
            ok();
            Intent intent = new Intent(FoodEditActivity.this, YinShiTJ.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_cancel_click) { //返回饮食推荐跳转按钮
            Intent intent = new Intent();
            intent.setClass(FoodEditActivity.this, YinShiTJ.class);
            startActivity(intent);
        }
    }

    //确定修改（保存）的点击事件处理
    public void ok() {
        final String _foodName = foodName.getText().toString().trim();
        final String _foodPrice = foodPrice.getText().toString().trim();
        final String _foodCalorie = foodCalorie.getText().toString().trim();

        if (TextUtils.isEmpty(_foodName)) {
            CommonUtils.showShortMsg(this, "请输入食物名称！");
            foodName.requestFocus();
        } else if (TextUtils.isEmpty(_foodPrice)) {
            CommonUtils.showShortMsg(this, "请输入食物价格！");
            foodPrice.requestFocus();
        } else if (TextUtils.isEmpty(_foodCalorie)) {
            CommonUtils.showShortMsg(this, "请输入食物热量！");
            foodCalorie.requestFocus();
        } else {
            foodInfoEdit.setFoodName(_foodName);
            foodInfoEdit.setFoodPrice(Integer.parseInt(_foodPrice));
            foodInfoEdit.setFoodCalorie(Float.parseFloat(_foodCalorie));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = foodDao.editFoodRecord(foodInfoEdit);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iRow > 0) {
                                CommonUtils.showShortMsg(getApplicationContext(), "食物信息已成功修改！");
                            } else {
                                CommonUtils.showShortMsg(getApplicationContext(), "修改食物信息失败！");
                            }
                            setResult(1);
                            finish();
                        }
                    });
                }
            }).start();
        }
    }

}