package com.example.eattolife;

import java.io.Serializable;

/**
 * 用户每日饮食记录实例
 */
public class FoodRecord implements Serializable {
    private String foodDate; //饮食日期
    private String foodMeal; //饮食餐别
    private float foodCalorie; //饮食热量
    private String foodPic; //食物图片

    public FoodRecord() {
    }
    public FoodRecord(String foodDate, String foodMeal, float foodCalorie, String foodPic) {
        this.foodDate = foodDate;
        this.foodMeal = foodMeal;
        this.foodCalorie = foodCalorie;
        this.foodPic = foodPic;
    }

    public String getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(String foodDate) {
        this.foodDate = foodDate;
    }

    public String getFoodMeal() {
        return foodMeal;
    }

    public void setFoodMeal(String foodMeal) {
        this.foodMeal = foodMeal;
    }

    public float getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(float foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }
}
