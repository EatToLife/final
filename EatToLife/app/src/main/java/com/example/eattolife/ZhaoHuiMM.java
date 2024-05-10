package com.example.eattolife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eattolife.tools.CommonUtils;

import java.util.Random;

public class ZhaoHuiMM extends AppCompatActivity implements View.OnClickListener {

    private String mCell;
    private String mCode;
    private EditText et_password_first;
    private EditText et_password_second;
    private EditText et_code;
    private Userinfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_hui_mm);
        et_password_first = findViewById(R.id.et_password_first);
        et_password_second = findViewById(R.id.et_password_second);
        et_code = findViewById(R.id.et_code);

        findViewById(R.id.b_code).setOnClickListener(this);
        findViewById(R.id.b_confirm).setOnClickListener(this);

        Intent intent = getIntent();// 获取传递的intent
        user = (Userinfo) intent.getSerializableExtra("user");
        //获取要修改密码的手机号
        mCell = user.getCell();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.b_code){
            //点击了“获取验证码”按钮
            //生成六位随机数字的验证码
            mCode = String.format("%06d", new Random().nextInt(999999));
            //以下弹出提醒对话框，提示用户记住六位验证码数字
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号"+mCell+"，本次验证码是"+mCode+"，请输入验证码");
            builder.setPositiveButton("好的",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (v.getId() == R.id.b_confirm) {
            //点击了“确定”按钮
            String password_first = et_password_first.getText().toString();
            String password_second = et_password_second.getText().toString();
            if(password_first.length()<6){
                Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password_first.equals(password_second)){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!mCode.equals(et_code.getText().toString())){
                Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                return;
            }
            UserDao userDao = new UserDao();//用户数据库操作实例
            int row = userDao.editUserPassword(user);
            if(row>0) {
                CommonUtils.showDlgMsg(ZhaoHuiMM.this, "密码修改成功");
                //以下把修改好的新密码返回上一个页面
                Intent intent = new Intent();
                intent.setClass(ZhaoHuiMM.this, ShenFenYZ.class);
                startActivity(intent);
            }
        }
    }
}