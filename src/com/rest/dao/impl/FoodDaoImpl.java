package com.rest.dao.impl;

import com.rest.dao.FoodDao;
import com.rest.domain.Condition;
import com.rest.domain.Food;
import com.rest.domain.PageBean;
import com.rest.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuero on 2017/7/17.
 */
public class FoodDaoImpl implements FoodDao {
    @Override
    public void add(Food food) {
        String sql = "insert into food(foodName,foodType_id,price,mprice,description,img) values(?,?,?,?,?,?)";
        try {
            JdbcUtils.getQuerryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),
                    food.getPrice(),food.getMprice(),food.getDescription(),food.getImg());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food food) {
        String sql = "update food set foodName=?,foodType_id=?,price=?,mprice=?,description=?,img=? where id=?";
        try {
            JdbcUtils.getQuerryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),
                    food.getPrice(),food.getMprice(),food.getDescription(),food.getImg(),food.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int foodId) {
        String sql = "delete from food where id=?";
        try {
            JdbcUtils.getQuerryRunner().update(sql,foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List findALL() {
        String sql = "select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.description,t.typeName from food f inner join foodtype t on f.foodType_id=t.id";
        try {
            return JdbcUtils.getQuerryRunner().query(sql.toString(),new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map findById(int foodId) {
        String sql = "select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.description,f.img,t.typeName from food f inner join foodtype t on f.foodType_id=t.id where f.id=?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql.toString(),new MapHandler(),foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List find(String foodName) {
        String sql = "select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.description,t.typeName from food f inner join foodtype t on f.foodType_id=t.id where f.foodName like ?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql.toString(),new MapListHandler(),"%"+foodName+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findALL(PageBean<Food> pb) {

        Condition condition = pb.getCondition();

        int typeId = condition.getFoodTypeId();
        String foodName = condition.getFoodName();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append("     f.id,");
        sb.append("     f.foodName,");
        sb.append("     f.foodType_id,");
        sb.append("     f.price,");
        sb.append("     f.mprice,");
        sb.append("     f.description,");
        sb.append("     f.img,");
        sb.append("     t.typeName ");
        sb.append("from ");
        sb.append("     food f ");
        sb.append("inner join ");
        sb.append("     foodtype t ");
        sb.append("on ");
        sb.append("     f.foodType_id=t.id ");
        sb.append("where 1=1 ");

        List<Object> list = new ArrayList<Object>();

        if(typeId>0){
            sb.append(" and f.foodType_id=? ");
            list.add(typeId);
        }

        if(foodName != null && !"".equals(foodName.trim())){
            sb.append(" and f.foodName like ? ");
            list.add(foodName);
        }

        sb.append(" LIMIT ?,?");
        int totalCount = getTotalCount(pb);
        pb.setTotalCount(totalCount);

        System.out.println(pb.getCurrentPage());
        if(pb.getCurrentPage()<1){
            pb.setCurrentPage(1);
        }else if (pb.getCurrentPage()>pb.getTotalPage()){
            pb.setCurrentPage(pb.getTotalPage());

        }
        System.out.println(pb.getCurrentPage());
        int index = (pb.getCurrentPage()-1)*pb.getPageCount();
        int count = pb.getPageCount();

        list.add(index);
        list.add(count);

        try {
            List<Food> list1 =  JdbcUtils.getQuerryRunner().query(sb.toString(),new BeanListHandler<Food>(Food.class),list.toArray());
            pb.setPageData(list1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Food> pb) {
        Condition condition = pb.getCondition();

        int typeId = condition.getFoodTypeId();
        String foodName = condition.getFoodName();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append("     count(*) ");
        sb.append("from ");
        sb.append("     food f ");
        sb.append("where 1=1 ");

        List<Object> list = new ArrayList<Object>();

        if(typeId>0){
            sb.append(" and f.foodType_id=? ");
            list.add(typeId);
        }

        if(foodName != null && !"".equals(foodName.trim())){
            sb.append(" and f.foodName like ? ");
            list.add(foodName);
        }

        try {
            Long num = JdbcUtils.getQuerryRunner().query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
            pb.setTotalCount(num.intValue());
            return num.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findObjectById(int foodId) {
        String sql = "select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.description,f.img from food f where f.id=?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql,new BeanHandler<Food>(Food.class),foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void test1(){
        FoodDao foodDao = new FoodDaoImpl();
        List<Map<String,Object>> all = foodDao.findALL();
        for (Map<String,Object> m : all){
            System.out.print("{ ");
            for (Map.Entry<String,Object> i : m.entrySet()){
                System.out.print(i.getKey()+": "+i.getValue()+", ");
            }
            System.out.println("}");
        }
    }

    @Test
    public void test2(){
        FoodDao foodDao = new FoodDaoImpl();
        Map<String,Object> food = foodDao.findById(2);

        for (Map.Entry<String,Object> i : food.entrySet()){
            System.out.print(i.getKey()+": "+i.getValue()+", ");
        }
    }

    @Test
    public void test3(){
        FoodDao foodDao = new FoodDaoImpl();
        List<Map<String,Object>> all = foodDao.find("aa");
        for (Map<String,Object> m : all){
            System.out.print("{ ");
            for (Map.Entry<String,Object> i : m.entrySet()){
                System.out.print(i.getKey()+": "+i.getValue()+", ");
            }
            System.out.println("}");
        }
    }

    @Test
    public void testAdd(){
        Food food = new Food("ppp",22,56.56,46.56,"good","url");
        FoodDao foodDao = new FoodDaoImpl();
        try{
            foodDao.add(food);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testUpdate(){
        Food food = new Food("eeee",12,56.56,46.56,"good","url");
        food.setId(5);
        FoodDao foodDao = new FoodDaoImpl();
        try{
            foodDao.update(food);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
