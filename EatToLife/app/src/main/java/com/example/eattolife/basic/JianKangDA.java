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
import com.example.eattolife.sql.UserDao;
import com.example.eattolife.user.Userinfo;
import com.example.eattolife.user.ZhangHaoXX;

import java.util.List;

public class JianKangDA extends AppCompatActivity {

    private String cell;
    private ImageButton me;
    private ImageView iv_return;
    private ImageView iv_edit1;
    private UserDao userDao;//用户数据库操作实例
    private List<Userinfo> userinfoList;//用户数据集合
    private LvUserinfoAdapter lvUserinfoAdapter;//用户信息数据适配器
    private ListView lv_user;//用户列表组件
    private Handler mainHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_da);

        me = findViewById(R.id.me);
        iv_return = findViewById(R.id.iv_return);
        iv_edit1 = findViewById(R.id.iv_edit1);
        userDao = new UserDao();
        lv_user = findViewById(R.id.lv_user);
        mainHandler = new Handler(getMainLooper());
        me.setOnClickListener(new MyOnClickListener());
        iv_return.setOnClickListener(new MyOnClickListener());

        loadUserDb();
    }

    private void loadUserDb() {
        Userinfo item = new Userinfo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //userinfoList = userDao.getAllUserList();//获取所有的用户数据
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //showLvData();
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

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_return) {
                finish();
            } else if (v.getId() == R.id.me) {
                Intent intent=new Intent();
                intent.setClass(JianKangDA.this, ZhangHaoXX.class);
                cell = getIntent().getStringExtra("cell");
                startActivity(intent);
            }

        }
    }
}