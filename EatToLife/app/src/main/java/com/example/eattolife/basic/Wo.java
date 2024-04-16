package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eattolife.AddFoodRecord;
import com.example.eattolife.R;

public class Wo extends AppCompatActivity {

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

        //早餐添加 跳转
        Button addBreakfast = findViewById(R.id.addBreakfast);
        addBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, AddFoodRecord.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });
    }
}