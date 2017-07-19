package com.rest.domain;

/**
 * Created by xuero on 2017/7/18.
 */
public class Condition {

    private String foodName;
    private int foodTypeId;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "foodName='" + foodName + '\'' +
                ", foodTypeId=" + foodTypeId +
                '}';
    }
}
