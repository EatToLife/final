package com.example.eattolife.TongJiQuShi;

public class FoodItem {
    private String name;
    private int weight;  // 克重
    private int calories;

    public FoodItem(String name, int calories, int i) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return name + " - " + weight + "克 - " + calories + "卡路里";
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }
}

