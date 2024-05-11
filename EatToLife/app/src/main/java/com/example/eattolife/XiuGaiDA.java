package com.example.eattolife;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eattolife.basic.JianKangDA;
import com.example.eattolife.sql.UserDao;
import com.example.eattolife.tools.CommonUtils;
import com.example.eattolife.user.Userinfo;

import java.util.Locale;

public class XiuGaiDA extends AppCompatActivity {
    private EditText et_age;
    private EditText et_sex;
    private EditText et_height;
    private EditText et_weight;
    private EditText et_foodLike;
    private EditText et_sportLike;
    private Button b_save;
    private ImageView xg_return;
    private Userinfo user;
    private int age;
    private String sex, foodLike, sportLike;
    private Double height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_da);

        et_age = findViewById(R.id.et_age);
        et_sex = findViewById(R.id.et_sex);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        et_foodLike = findViewById(R.id.et_foodLike);
        et_sportLike = findViewById(R.id.et_sportLike);
        b_save = findViewById(R.id.b_save);
        xg_return = findViewById(R.id.xg_return);


        b_save.setOnClickListener(new MyOnClickListener());
        xg_return.setOnClickListener(new MyOnClickListener());

        Intent intent = getIntent();// 获取传递的intent
        user = (Userinfo) intent.getSerializableExtra("user");
        if(user != null) {
            et_age.setText(String.valueOf(user.getAge()));
            et_sex.setText(user.getSex());
            et_height.setText(String.format(Locale.getDefault(), "%.1f", user.getHeight()));
            et_weight.setText(String.format(Locale.getDefault(), "%.2f", user.getWeight()));
            et_foodLike.setText(user.getFoodLike());
            et_sportLike.setText(user.getSportLike());
        }

    }

    public class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.b_save) {
                //获取修改后的信息
                String ageStr = et_age.getText().toString();
                sex = et_sex.getText().toString();
                String heightStr = et_height.getText().toString();
                String weightStr = et_weight.getText().toString();
                foodLike = et_foodLike.getText().toString();
                sportLike = et_sportLike.getText().toString();
                try {
                    age = Integer.parseInt(ageStr);
                    height = Double.parseDouble(heightStr);
                    weight = Double.parseDouble(weightStr);
                    if(age < 0 && height < 0 && weight < 0){
                        CommonUtils.showLongMsg(XiuGaiDA.this, "输入无效，请检查并重新输入");
                    }else {
                        EditUserinfo();
                    }
                }catch (NumberFormatException e){
                    CommonUtils.showLongMsg(XiuGaiDA.this, "输入无效，请检查并重新输入");
                    e.printStackTrace();
                }
                Intent intent=new Intent();
                intent.setClass(XiuGaiDA.this, JianKangDA.class);
                startActivity(intent);
            } else if ((v.getId() == R.id.xg_return)){
                Intent intent=new Intent();
                intent.setClass(XiuGaiDA.this, JianKangDA.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
        private void EditUserinfo() {
            new AsyncTask<Void, Void, Integer>(){
                @Override
                protected Integer doInBackground(Void... voids) {
                    try {
                        user.setAge(age);
                        user.setSex(sex);
                        user.setHeight(height);
                        user.setWeight(weight);
                        user.setFoodLike(foodLike);
                        user.setSportLike(sportLike);

                        UserDao userDao = new UserDao();//用户数据库操作实例
                        return userDao.updateUser(user);
                    }catch (Exception e){
                        e.printStackTrace();
                        return -1;
                    }
                }

                @Override
                protected void onPostExecute(Integer row){
                    if(row !=null && row.intValue() > 0) {
                        Log.d("XiuGaiDA", "更新成功");
                        CommonUtils.showShortMsg(XiuGaiDA.this, "保存成功");
                        finish();
                    }else{
                        Log.d("XiuGaiDA", "更新失败");
                    }
                }
            }.execute();


        }
    }
}