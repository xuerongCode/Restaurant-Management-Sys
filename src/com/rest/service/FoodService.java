package com.rest.service;

import com.rest.domain.Food;
import com.rest.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by xuero on 2017/7/17.
 */
public interface FoodService {

    List findAllFood();

    void deleteFood(int foodId);

    List findAllFoodByName(String foodName);

    Map findFoodById(int foodId);

    void updateFood(Food food);

    void addFood(Food food);

    Food findById(int id);

    void getAll(PageBean<Food> pb);
}
