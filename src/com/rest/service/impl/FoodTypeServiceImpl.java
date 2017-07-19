package com.rest.service.impl;

import com.rest.dao.FoodTypeDao;
import com.rest.domain.FoodType;
import com.rest.service.FoodTypeService;
import com.rest.utils.WebFactory;

import java.util.List;

/**
 * Created by xuero on 2017/7/3.
 */
public class FoodTypeServiceImpl implements FoodTypeService{

    private FoodTypeDao dao = WebFactory.getInstance("FoodTypeDao",FoodTypeDao.class);

    @Override
    public void save(FoodType foodType) {
        try {
            dao.save(foodType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        try {
            dao.update(foodType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String typeName) {
        try {
            return dao.getAll(typeName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FoodType findById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
