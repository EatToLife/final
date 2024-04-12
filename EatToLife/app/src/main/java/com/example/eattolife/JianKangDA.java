package com.example.eattolife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class JianKangDA extends AppCompatActivity {

    private ImageButton me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_da);

        me=findViewById(R.id.me);
        me.setOnClickListener(new MyOnClickListener());
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(JianKangDA.this, ZhangHaoXX.class);
                startActivity(intent);
        }
    }
}