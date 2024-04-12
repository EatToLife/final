package com.example.eattolife;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eattolife.util.ViewUtil;

import java.util.Random;

public class ShenFenYZ extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private TextView tv_password;
    private EditText et_password;
    private Button b_forget;
    private CheckBox cb_remember;
    private EditText et_cell;
    private RadioButton rb_password;
    private RadioButton rb_code;
    private ActivityResultLauncher<Intent> register;
    private Button b_login;
    private String mPassword = "111111";
    private String mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_fen_yz);
        RadioGroup rb_login = findViewById(R.id.rg_login);
        tv_password = findViewById(R.id.tv_password);
        et_cell = findViewById(R.id.et_cell);
        et_password = findViewById(R.id.et_password);
        b_forget = findViewById(R.id.b_forget);
        cb_remember = findViewById(R.id.cb_remember);
        rb_password = findViewById(R.id.rb_password);
        rb_code = findViewById(R.id.rb_code);
        b_login = findViewById(R.id.b_login);
        //给rg_login设置单选监听器
        rb_login.setOnCheckedChangeListener(this);
        //给et_cell添加文本变更监听器
        et_cell.addTextChangedListener(new HideTextWatcher(et_cell, 11));
        //给et_password添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
        b_forget.setOnClickListener(this);
        b_login.setOnClickListener(this);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                if(intent != null && result.getResultCode() == Activity.RESULT_OK){
                    //用户密码已改为新密码，故更新密码变量
                    mPassword = intent.getStringExtra("new_password");
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.rb_password) {//选择密码登录
            tv_password.setText(getString(R.string.password));
            et_password.setHint(getString(R.string.input_password));
            b_forget.setText(getString(R.string.forget_password));
            cb_remember.setVisibility(View.VISIBLE);

        } else if (checkedId == R.id.rb_code) {//选择验证码登录
            tv_password.setText(getString(R.string.code));
            et_password.setHint(getString(R.string.input_code));
            b_forget.setText(getString(R.string.get_code));
            cb_remember.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        String cell = et_cell.getText().toString();
        if (v.getId() == R.id.b_forget) {
            if (cell.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            //选择了密码方式校验，此时要跳到找回密码页面
            if (rb_password.isChecked()) {
                //以下携带手机号跳转到找回密码页面
                Intent intent = new Intent(this, ZhaoHuiMM.class);
                intent.putExtra("cell", cell);
                register.launch(intent);
            } else if (rb_code.isChecked()) {
                //生成六位随机数字的验证码
                mCode = String.format("%06d", new Random().nextInt(999999));
                //以下弹出提醒对话框，提示用户记住六位验证码数字
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + cell + "，本次验证码是" + mCode + "，请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }else if(v.getId() == R.id.b_login){
            //密码方式校验
            if(rb_password.isChecked()){
                if(!mPassword.equals((et_password.getText().toString()))){
                    Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                //提示用户登录成功
                loginSuccess();
            } else if (rb_code.isChecked()) {
                //验证码方式校验
                if(!mCode.equals((et_password.getText().toString()))){
                    Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                //提示用户登录成功
                loginSuccess();
            }
        }
    }

    //校验通过，登录成功
    private void loginSuccess() {
        String desc = String.format("您的手机号是%s，恭喜你通过登录验证，点击”确定“按钮返回上个页面",
                et_cell.getText().toString());
        //以下弹出提醒对话框，提示用户登录成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定",(dialog, which) -> {
            //结束当前的活动页面
            //finish();
            //登录成功跳转到健康档案界面
            Intent intent = new Intent(this, JianKangDA.class);
            intent.putExtra("phone",et_cell.getText().toString());
            register.launch(intent);
        });
        builder.setNegativeButton("我再看看",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
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