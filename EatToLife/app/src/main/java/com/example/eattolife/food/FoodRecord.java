package com.example.eattolife.food;

import java.io.Serializable;

/**
 * 用户每日饮食记录实例
 */
public class FoodRecord implements Serializable {
    private int foodRecordID; //饮食记录ID
    private String foodRecordDt; //饮食记录日期
    private String foodRecordName; //饮食记录名字
    private float foodRecordCalorie; //饮食记录热量

    public FoodRecord() {
    }
    public FoodRecord(int foodRecordID, String foodRecordDt, String foodRecordName, float foodRecordCalorie) {
        this.foodRecordID = foodRecordID;
        this.foodRecordDt = foodRecordDt;
        this.foodRecordName = foodRecordName;
        this.foodRecordCalorie = foodRecordCalorie;
    }

    public int getFoodRecordID() {
        return foodRecordID;
    }

    public void setFoodRecordID(int foodRecordID) {
        this.foodRecordID = foodRecordID;
    }

    public String getFoodRecordDt() {
        return foodRecordDt;
    }

    public void setFoodRecordDt(String foodRecordDt) {
        this.foodRecordDt = foodRecordDt;
    }

    public String getFoodRecordName() {
        return foodRecordName;
    }

    public void setFoodRecordName(String foodRecordName) {
        this.foodRecordName = foodRecordName;
    }

    public float getFoodRecordCalorie() {
        return foodRecordCalorie;
    }

    public void setFoodRecordCalorie(float foodRecordCalorie) {
        this.foodRecordCalorie = foodRecordCalorie;
    }
}
