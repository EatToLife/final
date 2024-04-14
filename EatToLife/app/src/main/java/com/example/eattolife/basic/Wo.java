package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eattolife.AddFoodRecord;
import com.example.eattolife.ExerciseDao;
import com.example.eattolife.FoodDao;
import com.example.eattolife.TodayDao;
import com.example.eattolife.R;
import com.example.eattolife.User;
import com.example.eattolife.UserDao;

public class Wo extends AppCompatActivity {

    // 添加成员变量来存储UI组件引用
    private TextView textViewCalorieBalance;
    private TextView textViewCalorieNeeds;
    private TextView textViewCalorieConsumed;
    private TextView textViewCalorieBurned;


    // 获取数据库中的信息
    private UserDao userDao;
    private TodayDao todayDao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo);

        textViewCalorieBalance = findViewById(R.id.textViewCalorieBalance);
        textViewCalorieNeeds = findViewById(R.id.textViewCalorieNeeds);
        textViewCalorieConsumed = findViewById(R.id.textViewCaloriesConsumed);
        textViewCalorieBurned = findViewById(R.id.textViewCaloriesBurned);


        userDao = new UserDao(this);
        todayDao = new TodayDao(this);

        // calculateCalorieBalance() 方法，用于计算卡路里平衡度
        calculateCalorieBalance();



        //早餐推荐 跳转
        Button breakfast = findViewById(R.id.breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, YinShiTJ.class); //从Wo跳转到YinShiTJ
                startActivity(intent);
            }
        });

        //早餐添加 跳转
        Button addBreakfast = findViewById(R.id.addBreakfast);
        addBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wo.this, AddFoodRecord.class); //从Wo跳转到AddFoodRecord
                startActivity(intent);
            }
        });
    }

    private void calculateCalorieBalance() {
        // 获取用户身体数据
        User user = userDao.getUserProfile();
        if (user == null) {
            textViewCalorieBalance.setText("用户资料未设置");
            return;
        }

        // 获取用户身体数据，这些数据可以从用户设置中获取或要求用户输入
        double weight = getUserWeight(); // 获取用户体重
        double height = getUserHeight(); // 获取用户身高
        int age = getUserAge(); // 获取用户年龄
        boolean isMale = getUserGender(); // 获取用户性别

        // 计算BMR
        double bmr = calculateBMR(isMale, weight, height, age);

        // 从数据库获取今天所有餐别的摄入卡路里总和
        double totalCaloriesConsumed = todayDao.getTodayTotalCalories();

        // 从数据库获取今天的运动消耗卡路里总和
        double totalCaloriesBurned = todayDao.getTodayTotalCaloriesBurned();

        // 用户的日常活动水平，活动系数一般取1.4
        double activityLevel = 1.4;

        // 计算日常所需卡路里
        double dailyCalorieNeeds = bmr * activityLevel;

        // 计算卡路里平衡度
        double calorieBalance = dailyCalorieNeeds - totalCaloriesConsumed + totalCaloriesBurned;

        // 显示卡路里平衡度
        runOnUiThread(() -> {
            textViewCalorieNeeds.setText(String.format("%.0f", dailyCalorieNeeds));
            textViewCalorieConsumed.setText(String.format("%.0f", totalCaloriesConsumed));
            textViewCalorieBurned.setText(String.format("%.0f", totalCaloriesBurned));
            textViewCalorieBalance.setText(String.format("%.0f", calorieBalance));
        });
    }


    // 根据性别、体重、身高和年龄计算BMR的方法，与之前示例相同
    private double calculateBMR(boolean isMale, double weight, double height, int age) {
        if (isMale) {
            return 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
    }

    private double getUserWeight() {
        // user 是从数据库加载的用户个人资料对象
        User user = userDao.getUserProfile();
        return user.getWeight();
    }

    private double getUserHeight() {
        User user = userDao.getUserProfile();
        return user.getHeight();
    }

    private int getUserAge() {
        User user = userDao.getUserProfile();
        return user.getAge();
    }

    private boolean getUserGender() {
        User user = userDao.getUserProfile();
        // 性别用布尔值存储：true 表示男性，false 表示女性
        return user.isMale();
    }
}