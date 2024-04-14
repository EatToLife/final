package com.example.eattolife;
import java.io.Serializable;

/**
 * 用户信息实体类
 */
public class Userinfo implements Serializable{
    private int userID;
    private String userName;
    private String cell;
    private String password;
    private int age;
    private String sex;
    private Double height;
    private Double weight;
    private String foodLike;
    private String sportLike;
    public Userinfo(){
    }
    public Userinfo(int userID, String userName, String cell, String password, int age, String sex, Double height, Double weight, String foodLike, String sportLike){
        this.userID = userID;
        this.userName = userName;
        this.cell = cell;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.foodLike = foodLike;
        this.sportLike = sportLike;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFoodLike() {
        return foodLike;
    }

    public void setFoodLike(String foodLike) {
        this.foodLike = foodLike;
    }

    public String getSportLike() {
        return sportLike;
    }

    public void setSportLike(String sportLike) {
        this.sportLike = sportLike;
    }
}
