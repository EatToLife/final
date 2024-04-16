package com.example.eattolife;

import java.io.Serializable;

/**
 * 用户每日运动记录实例
 */
public class ExerciseRecord implements Serializable {
    private String exerciseDate; // 运动日期
    private String exerciseType; // 运动类型
    private float caloriesBurned; // 燃烧的卡路里

    public ExerciseRecord() {
    }

    public ExerciseRecord(String exerciseDate, String exerciseType, float caloriesBurned, String exercisePic) {
        this.exerciseDate = exerciseDate;
        this.exerciseType = exerciseType;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }


}
