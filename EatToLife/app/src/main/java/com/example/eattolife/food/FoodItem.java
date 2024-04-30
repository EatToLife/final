package com.example.eattolife.food;

public class FoodItem {
    private String name;
    private int calories;

    public FoodItem(String name, int calories, int i) {
        this.name = name;
        this.calories = calories;
    }

    /// Getters
    public String getName() {
        return name;
    }


    public int getCalories() {
        return calories;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


}

