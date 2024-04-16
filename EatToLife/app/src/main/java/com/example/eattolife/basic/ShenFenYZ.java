package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eattolife.R;
import com.example.eattolife.UserDao;
import com.example.eattolife.Userinfo;
import com.example.eattolife.ZhaoHuiMM;
import com.example.eattolife.tools.CommonUtils;
import com.example.eattolife.util.ViewUtil;


public class ShenFenYZ extends AppCompatActivity implements View.OnClickListener {

    private EditText et_cell, et_password;
    private UserDao userDao;//用户数据库操作类
    private Handler mainHandler;//主线程

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_fen_yz);

        et_cell = findViewById(R.id.et_cell);
        et_password = findViewById(R.id.et_password);
        TextView tv_forget = findViewById(R.id.tv_forget);
        Button b_login = findViewById(R.id.b_login);
        //给et_cell添加文本变更监听器
        et_cell.addTextChangedListener(new HideTextWatcher(et_cell, 11));
        //给et_password添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
        tv_forget.setOnClickListener(this);
        b_login.setOnClickListener(this);
        mainHandler = new Handler(getMainLooper());
        userDao = new UserDao();

    }

    @Override
    public void onClick(View v) {
        String cell = et_cell.getText().toString();
        if (v.getId() == R.id.tv_forget) {
            if (cell.length() < 11) {
                CommonUtils.showDlgMsg(ShenFenYZ.this, "请输入正确的手机号");
            }else {
                Intent intent = new Intent(ShenFenYZ.this, ZhaoHuiMM.class);
                intent.putExtra("cell", cell);
                startActivity(intent);
            }
        }else if(v.getId() == R.id.b_login){
            doLogin();
        }
    }

    //执行登录操作
    private void doLogin() {
        String cell = et_cell.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if(TextUtils.isEmpty(cell)){
            CommonUtils.showDlgMsg(ShenFenYZ.this, "请输入手机号");
            et_cell.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            CommonUtils.showDlgMsg(ShenFenYZ.this, "请输入密码");
            et_password.requestFocus();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Userinfo item = userDao.getUserByCellAndPassword(cell, password);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //if (item == null) {
                            //CommonUtils.showDlgMsg(ShenFenYZ.this, "手机号或密码错误");
                            //} else {
                            CommonUtils.showLongMsg(ShenFenYZ.this, "登录成功，进入健康档案");
                            Intent intent = new Intent();
                            intent.setClass(ShenFenYZ.this, Wo.class);
                            startActivity(intent);
                            //}
                        }
                    });
                }
            }).start();
        }
    }

    private class HideTextWatcher implements TextWatcher {
        final private EditText mView;
        final private int mMaxLength;
        public HideTextWatcher(EditText v, int maxLength) {
            this.mView = v;
            this.mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == mMaxLength){
                //隐藏输入法软键盘
                ViewUtil.hideOneInputMethod(ShenFenYZ.this, mView);
            }
        }
    }
}