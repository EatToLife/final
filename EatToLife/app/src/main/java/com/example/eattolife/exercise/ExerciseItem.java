package com.example.eattolife.exercise;

public class ExerciseItem {
    private String name;
    private int calories;

    public ExerciseItem(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}
