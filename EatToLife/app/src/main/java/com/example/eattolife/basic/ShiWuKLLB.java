package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eattolife.FoodItem;
import com.example.eattolife.R;

import java.util.ArrayList;
import java.util.List;

public class ShiWuKLLB extends AppCompatActivity {

    EditText editTextChaXunSW;
    ImageButton buttonChaXunSW;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_wu_kllb);

        editTextChaXunSW = findViewById(R.id.ChaXunSW);
        buttonChaXunSW = findViewById(R.id.bt_ChaXunSW);
        listView = findViewById(R.id.ShiWuKLLB);

        // Initialize static list of foods
        List<FoodItem> allFoods = initializeStaticFoodList();
        List<String> foodDescriptions = new ArrayList<>();
        for (FoodItem item : allFoods) {
            foodDescriptions.add(item.getName() + " - " + item.getCalories() + "千卡/100克");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodDescriptions);
        listView.setAdapter(adapter);

        buttonChaXunSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = editTextChaXunSW.getText().toString();
                if (!foodName.isEmpty()) {
                    List<String> filteredDescriptions = filterFoods(foodName, allFoods);
                    adapter.clear();
                    adapter.addAll(filteredDescriptions);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "请输入食物名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<FoodItem> initializeStaticFoodList() {
        List<FoodItem> foods = new ArrayList<>();
        foods.add(new FoodItem("苹果", 52, 95));
        foods.add(new FoodItem("香蕉", 89, 95));
        foods.add(new FoodItem("米饭", 130, 95));
        foods.add(new FoodItem("牛油果", 160, 95));
        foods.add(new FoodItem("杏仁", 576, 95));
        foods.add(new FoodItem("西兰花", 34, 95));
        foods.add(new FoodItem("鸡胸肉", 165, 95));
        foods.add(new FoodItem("鸡蛋", 155, 95));
        foods.add(new FoodItem("大蒜", 149, 95));
        foods.add(new FoodItem("葡萄", 69, 95));
        foods.add(new FoodItem("胡萝卜", 41, 95));
        foods.add(new FoodItem("奶酪", 402, 95));
        foods.add(new FoodItem("樱桃", 50, 95));
        foods.add(new FoodItem("玉米", 96, 95));
        foods.add(new FoodItem("黄瓜", 16, 95));
        foods.add(new FoodItem("青豆", 31, 95));
        foods.add(new FoodItem("蜂蜜", 304, 95));
        foods.add(new FoodItem("牛奶", 42, 95));
        foods.add(new FoodItem("菠菜", 23, 95));
        foods.add(new FoodItem("番茄", 18, 95));
        foods.add(new FoodItem("土豆", 77, 95));
        foods.add(new FoodItem("南瓜", 26, 95));
        foods.add(new FoodItem("黑巧克力", 546, 95));
        foods.add(new FoodItem("大豆", 446, 95));
        foods.add(new FoodItem("草莓", 32, 95));
        foods.add(new FoodItem("糖", 387, 95));
        foods.add(new FoodItem("牛肉", 250, 95));
        foods.add(new FoodItem("羊肉", 294, 95));
        foods.add(new FoodItem("培根", 541, 95));
        foods.add(new FoodItem("鳗鱼", 184, 95));
        foods.add(new FoodItem("豆腐", 76, 95));
        return foods;
    }


    private List<String> filterFoods(String query, List<FoodItem> foods) {
        List<String> filtered = new ArrayList<>();
        for (FoodItem item : foods) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(item.getName() + " - " + item.getCalories() + "千卡/100克");
            }
        }
        return filtered;
    }
}




//package com.example.eattolife.basic;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.eattolife.FoodItem;
//import com.example.eattolife.R;
//import com.example.eattolife.ShiWuSJK;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShiWuKLLB extends AppCompatActivity {
//
//    EditText editTextChaXunSW;
//    ImageButton buttonChaXunSW;
//    ListView listView;
//    ShiWuSJK foodDao;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shi_wu_kllb);
//
//        editTextChaXunSW = findViewById(R.id.ChaXunSW);
//        buttonChaXunSW = findViewById(R.id.bt_ChaXunSW);
//        listView = findViewById(R.id.ShiWuKLLB);
//
//        foodDao = new ShiWuSJK(); // 初始化 FoodDao
//        List<FoodItem> allFoods = foodDao.getAllFoodList(); // 从数据库获取食物列表
//        List<String> foodDescriptions = new ArrayList<>();
//        for (FoodItem item : allFoods) {
//            foodDescriptions.add(item.getName() + " - " + item.getCalories() + "千卡/100克");
//        }
//
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodDescriptions);
//        listView.setAdapter(adapter);
//
//        buttonChaXunSW.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String foodName = editTextChaXunSW.getText().toString();
//                if (!foodName.isEmpty()) {
//                    List<String> filteredDescriptions = filterFoods(foodName, allFoods);
//                    adapter.clear();
//                    adapter.addAll(filteredDescriptions);
//                    adapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getApplicationContext(), "请输入食物名称", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//
//    private List<String> filterFoods(String query, List<FoodItem> foods) {
//        List<String> filtered = new ArrayList<>();
//        for (FoodItem item : foods) {
//            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
//                filtered.add(item.getName() + " - " + item.getCalories() + "千卡/100克");
//            }
//        }
//        return filtered;
//    }
//}
