package com.example.eattolife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.eattolife.R;
import com.example.eattolife.food.FoodItem;

import java.util.List;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

    // 构造函数
    public FoodItemAdapter(Context context, List<FoodItem> foodItems) {
        super(context, 0, foodItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 检查现有视图是否被重用，否则膨胀视图
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_food, parent, false);
        }

        // 获取当前项的实例
        FoodItem foodItem = getItem(position);

        // 找到布局中的 TextView 并设置文本
        TextView nameTextView = convertView.findViewById(R.id.textViewFoodName);
        TextView caloriesTextView = convertView.findViewById(R.id.textViewCalories);

        nameTextView.setText(foodItem.getName());
        caloriesTextView.setText(String.format("%d卡路里", foodItem.getCalories()));

        return convertView;
    }
}
