package com.example.eattolife;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ZhangHaoXX extends AppCompatActivity {

    private Button b_change_password;
    private EditText et_name;
    private EditText et_phone;
    private String mName;
    private String mCell;
    private Userinfo user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_hao_xx);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        b_change_password = findViewById(R.id.b_change_password);
        b_change_password.setOnClickListener(this::onClick);

        Intent intent = getIntent();// 获取传递的intent
        user = (Userinfo) intent.getSerializableExtra("user");
        mName = user.getUserName();
        mCell = user.getCell();
        et_name.setText(mName);
        et_phone.setText(mCell);

    }

    public void onClick(View v) {
        Intent intent = new Intent(this, ZhaoHuiMM.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}