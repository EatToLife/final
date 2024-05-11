package com.example.eattolife.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eattolife.R;
import com.example.eattolife.basic.ShenFenYZ;
import com.example.eattolife.sql.UserDao;
import com.example.eattolife.tools.CommonUtils;

import java.util.Random;

public class ZhaoHuiMM extends AppCompatActivity implements View.OnClickListener {

    private String mCell;
    private String mCode;
    private String password;
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
        if(user != null){
            mCell = user.getCell();
        }else {
            CommonUtils.showLongMsg(ZhaoHuiMM.this, "未能获得用户信息");
            finish();
        }
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
            password = et_password_first.getText().toString();
            String password_second = et_password_second.getText().toString();
            if(password.length()<6){
                Toast.makeText(this,"请输入6位以上的密码",Toast.LENGTH_SHORT).show();
                return;
            } else if(!password.equals(password_second)){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                return;
            } else if(!mCode.equals(et_code.getText().toString())){
                Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                return;
            }else {
                EditPassword();
            }
        }
    }
    private void EditPassword() {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    user.setPassword(password);

                    UserDao userDao = new UserDao();//用户数据库操作实例
                    return userDao.editUserPassword(user);
                }catch (Exception e){
                    e.printStackTrace();
                    return -1;
                }
            }

            @Override
            protected void onPostExecute(Integer row){
                if(row !=null && row > 0) {
                    Log.d("ZhaoHuiMM", "修改成功");
                    CommonUtils.showShortMsg(ZhaoHuiMM.this, "密码修改成功");
                    finish();
                }else{
                    Log.d("ZhaoHuiMM", "修改失败");
                }
            }
        }.execute();
    }
}