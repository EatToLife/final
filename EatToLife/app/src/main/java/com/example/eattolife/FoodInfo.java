package com.example.eattolife;

import java.io.Serializable;

/**
 * 用户每日饮食记录实例
 */
public class FoodInfo implements Serializable {
    private int foodID; //食物编号
    private String foodName; //食物名称
    private int foodPrice; //食物价格
    private float foodCalorie; //食物热量
    //private String foodPic; //食物图片

    public FoodInfo() {
    }
    public FoodInfo(int foodID, String foodName, int foodPrice, float foodCalorie) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCalorie = foodCalorie;
       // this.foodPic = foodPic;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public float getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(float foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

//    public String getFoodPic() {
//        return foodPic;
//    }
//
//    public void setFoodPic(String foodPic) {
//        this.foodPic = foodPic;
//    }
}
