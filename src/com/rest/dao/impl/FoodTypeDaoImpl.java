package com.rest.dao.impl;

import com.rest.dao.FoodTypeDao;
import com.rest.domain.FoodType;
import com.rest.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by xuero on 2017/7/3.
 */
public class FoodTypeDaoImpl implements FoodTypeDao{
    @Override
    public void save(FoodType foodType) {
        String sql="INSERT INTO foodType(typeName) VALUE(?)";
        try{
            JdbcUtils.getQuerryRunner().update(sql,foodType.getTypeName());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        String sql = "update foodType set typeName = ? where id = ? ";
        try{
            JdbcUtils.getQuerryRunner().update(sql,foodType.getTypeName(),foodType.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql="delete from foodType where id = ?";
        try{
            JdbcUtils.getQuerryRunner().update(sql,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        String sql = "select * from foodType";
        try{
            return JdbcUtils.getQuerryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String typeName) {
        String sql = "select * from foodType where typeName like ?";
        try{
            return JdbcUtils.getQuerryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),"%"+typeName+"%");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public FoodType findById(int id) {
        String sql = "select * from foodType where id = ?";
        try{
            return JdbcUtils.getQuerryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
