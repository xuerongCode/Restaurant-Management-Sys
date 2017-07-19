package com.rest.domain;

/**
 * Created by xuero on 2017/7/17.
 */
public class Food {
    int id;
    String foodName;
    int foodType_id;
    double price;
    double mprice;
    String description;
    String img;

    public Food(){}

    public Food(String foodName, int foodType_id, double price, double mprice, String description, String img) {
        this.foodName = foodName;
        this.foodType_id = foodType_id;
        this.price = price;
        this.mprice = mprice;
        this.description = description;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodType_id() {
        return foodType_id;
    }

    public void setFoodType_id(int foodType_id) {
        this.foodType_id = foodType_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodType_id=" + foodType_id +
                ", price=" + price +
                ", mprice=" + mprice +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
