package com.rest.dao;

import com.rest.domain.Food;
import com.rest.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by xuero on 2017/7/17.
 */
public interface FoodDao {

    void add(Food food);
    void update(Food food);
    void delete(int foodId);
    List findALL();
    Map findById(int foodId);
    List find(String foodName);

    void findALL(PageBean<Food> pb);
    int getTotalCount(PageBean<Food> pb);
    Food findObjectById(int foodId);


}
