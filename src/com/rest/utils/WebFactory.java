package com.rest.utils;

import com.rest.dao.FoodTypeDao;

import java.io.FileReader;
import java.util.ResourceBundle;

/**
 * Created by xuero on 2017/7/3.
 */
public class WebFactory {
    private static ResourceBundle bundle;
    static {
        bundle=ResourceBundle.getBundle("service_dao_mapping");
    }
    public static <T> T getInstance(String daoClassName,Class<T> clazz){
        String className = bundle.getString(daoClassName);
        try {
            return (T)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
