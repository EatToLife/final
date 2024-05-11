package com.example.eattolife.basic;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.eattolife.LvUserinfoAdapter;
import com.example.eattolife.R;
import com.example.eattolife.XiuGaiDA;
import com.example.eattolife.user.ZhangHaoXX;
import com.example.eattolife.sql.UserDao;
import com.example.eattolife.user.Userinfo;

import java.util.List;
import java.util.Locale;

public class JianKangDA extends AppCompatActivity {

    private ImageButton me;
    private ImageView da_return;
    private ImageView iv_edit;
    private TextView tv_userName;
    private TextView tv_BMI;
    private TextView tv_tzl;
    private UserDao userDao;//用户数据库操作实例
    private Userinfo user;
    private List<Userinfo> userinfoList;//用户数据集合
    private LvUserinfoAdapter lvUserinfoAdapter;//用户信息数据适配器
    private ListView lv_user;//用户列表组件
    private Handler mainHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_da);

        me = findViewById(R.id.me);
        da_return = findViewById(R.id.da_return);
        iv_edit = findViewById(R.id.iv_edit);
        tv_userName = findViewById(R.id.tv_userName);
        tv_BMI = findViewById(R.id.tv_BMI);
        tv_tzl = findViewById(R.id.tv_tzl);
        userDao = new UserDao();
        lv_user = findViewById(R.id.lv_user);
        mainHandler = new Handler(getMainLooper());
        me.setOnClickListener(new MyOnClickListener());
        da_return.setOnClickListener(new MyOnClickListener());
        iv_edit.setOnClickListener(new MyOnClickListener());
        loadUserDb();

        Intent intent = getIntent();// 获取传递的intent
        user = (Userinfo) intent.getSerializableExtra("currentUser");
        double bmi = 0,tzl = 0;
        String tizhi = "", userName = "";
        if(user != null){
            userName = user.getUserName();
            double height = user.getHeight();
            double weight =user.getWeight();
            int age = user.getAge();
            String sex = user.getSex();
            bmi =10000 * weight / (height * height);//计算当前BMI
            if (sex == "女"){
                tzl = (1.20 * bmi) + (0.23 * age) - 5.4;
            }else {
                tzl = (1.20 * bmi) + (0.23 * age) - 16.2;
            }
        }
        if (bmi < 18.5){
            tizhi = "（消瘦）";
        } else if (bmi <= 23.9) {
            tizhi = "（正常）";
        } else if (bmi <=27.9) {
            tizhi = "（超重）";
        }else {
            tizhi = "（肥胖）";
        }
        tv_userName.setText(userName);
        tv_BMI.setText("当前BMI："+String.format(Locale.getDefault(), "%.1f", bmi)+tizhi);
        tv_tzl.setText("当前体脂率："+String.format(Locale.getDefault(), "%.1f", tzl)+"%");
    }

    private void loadUserDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userinfoList = (List<Userinfo>) userDao.getAllUserList();//获取所有的用户数据
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLvData();
                    }
                });
            }
        }).start();
    }

    //显示列表数据的方法
    private void showLvData(){
        if(lvUserinfoAdapter == null){//首次加载时的操作
            lvUserinfoAdapter = new LvUserinfoAdapter(this, userinfoList);
            lv_user.setAdapter(lvUserinfoAdapter);
        }else{//更新数据时的操作
            lvUserinfoAdapter.setUserinfoList(userinfoList);
            lvUserinfoAdapter.notifyDataSetChanged();
        }
    }

    //修改按钮的操作


    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.da_return) {
                Intent intent=new Intent();
                intent.setClass(JianKangDA.this, Wo.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (v.getId() == R.id.me) {
                Intent intent=new Intent();
                intent.setClass(JianKangDA.this, ZhangHaoXX.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (v.getId() == R.id.iv_edit) {
                Intent intent=new Intent();
                intent.setClass(JianKangDA.this, XiuGaiDA.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }

        }
    }
}