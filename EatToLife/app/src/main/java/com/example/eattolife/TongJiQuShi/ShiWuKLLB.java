package com.example.eattolife.TongJiQuShi;

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
    List<FoodItem> allFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_wu_kllb);

        editTextChaXunSW = findViewById(R.id.ChaXunSW);
        buttonChaXunSW = findViewById(R.id.bt_ChaXunSW);
        listView = findViewById(R.id.ShiWuKLLB);

        initializeFoodData();

        com.example.eattolife.FoodItemAdapter adapter = new com.example.eattolife.FoodItemAdapter(this, allFoods);
        listView.setAdapter(adapter);

        buttonChaXunSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = editTextChaXunSW.getText().toString();
                if (!foodName.isEmpty()) {
                    List<FoodItem> filteredFoods = filterFoods(foodName);
                    updateListView(filteredFoods);
                } else {
                    Toast.makeText(getApplicationContext(), "请输入食物名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeFoodData() {
        allFoods.add(new FoodItem("苹果", 150, 95));
        allFoods.add(new FoodItem("香蕉", 120, 105));
        allFoods.add(new FoodItem("汉堡", 200, 295));
        allFoods.add(new FoodItem("披萨", 250, 285));
        allFoods.add(new FoodItem("沙拉", 100, 150));
    }

    private List<FoodItem> filterFoods(String query) {
        List<FoodItem> filtered = new ArrayList<>();
        for (FoodItem item : allFoods) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    private void updateListView(List<FoodItem> results) {
        ArrayAdapter<FoodItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);
    }
}
