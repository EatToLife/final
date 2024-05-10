package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eattolife.food.FoodAddActivity;
import com.example.eattolife.R;

public class Wo extends AppCompatActivity {
    private String cell;
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MusicServer.stop(this);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MusicServer.play(this, R.raw.loop);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo);

        //早餐推荐 跳转
        Button breakfast = findViewById(R.id.breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, YinShiTJ.class); //从Wo跳转到YinShiTJ
                startActivity(intent);
            }
        });

        //饮食记录 跳转
        Button foodRecordQuery = findViewById(R.id.foodRecordQuery);
        foodRecordQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, FoodAddActivity.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });

        //健康档案 跳转
        Button jianKangDA = findViewById(R.id.jianKangDA);
        jianKangDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, JianKangDA.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });
        //网页跳转
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Wo.this, InternetSearch.class); //
                startActivity(intent);
            }
        });
        //健康统计 跳转
        Button jianKangTJ = findViewById(R.id.jianKangTJ);
        jianKangTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, JianKangTJ.class); //从Wo跳转到AddFoodRecord
                cell = getIntent().getStringExtra("cell");
                intent.putExtra("cell", cell);
                startActivity(intent);
            }
        });

        //食物卡路里查询
        Button foodKLLQuery = findViewById(R.id.foodKLLQuery);
        foodKLLQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, ShiWuKLLB.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });

        //食物卡路里查询
        Button YunDongKLLBQuery = findViewById(R.id.YunDongKLLBQuery);
        YunDongKLLBQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, YunDongKLLB.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });


    }
}