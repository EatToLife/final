package com.example.eattolife.user;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eattolife.R;

public class ZhangHaoXX extends AppCompatActivity {

    private Button b_change_password;
    private EditText et_name;
    private EditText et_phone;
    private String cell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_hao_xx);
        et_name = findViewById(R.id.et_name);
        et_name.setText("fannie");
        et_phone = findViewById(R.id.et_phone);
        //从上个页面获取要修改密码的手机号
        cell = getIntent().getStringExtra("cell");
        et_phone.setText(cell);
        b_change_password = findViewById(R.id.b_change_password);
        b_change_password.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, ZhaoHuiMM.class);
        startActivity(intent);
    }

}