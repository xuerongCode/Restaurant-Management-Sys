package com.rest.dao;

import com.rest.domain.FoodType;

import java.util.List;

/**
 * Created by xuero on 2017/7/3.
 */
public interface FoodTypeDao {

    /**
     * add
     */
    void save(FoodType foodType);

    /**
     * update
     */
    void update(FoodType foodType);

    /**
     * delete
     */
    void delete(int id);

    /**
     * find all
     */
    List<FoodType> getAll();

    /**
     * find FoodType via typeName
     */
    List<FoodType> getAll(String typeName);

    /**
     * find item via primary key
     */
    FoodType findById(int id);
}
