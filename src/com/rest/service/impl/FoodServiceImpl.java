package com.rest.service.impl;

import com.rest.dao.FoodDao;
import com.rest.domain.Food;
import com.rest.domain.PageBean;
import com.rest.service.FoodService;
import com.rest.utils.WebFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xuero on 2017/7/17.
 */
public class FoodServiceImpl implements FoodService {

    FoodDao dao = WebFactory.getInstance("FoodDao",FoodDao.class);

    @Override
    public List findAllFood() {

        return dao.findALL();
    }

    @Override
    public void deleteFood(int foodId) {
        dao.delete(foodId);
    }

    @Override
    public List findAllFoodByName(String foodName) {
        return dao.find(foodName);
    }

    @Override
    public Map findFoodById(int foodId) {
        return dao.findById(foodId);
    }

    @Override
    public void updateFood(Food food) {
        dao.update(food);
    }

    @Override
    public void addFood(Food food) {
        dao.add(food);
    }

    @Override
    public Food findById(int id) {
        try {
            return dao.findObjectById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Food> pb) {
        try {
            dao.findALL(pb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
