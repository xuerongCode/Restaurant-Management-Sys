package com.rest.dao.impl;

import com.rest.dao.DinnerTableDao;
import com.rest.domain.DinnerTable;
import com.rest.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;


import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xuero on 2017/7/13.
 */
public class DinnerTableDaoImpl implements DinnerTableDao {

    @Override
    public void add(DinnerTable dinnerTable) {
        String sql = "insert into dinnerTable(tableName,tableStatus,orderDate) value(?,?,?)";
        try {
            JdbcUtils.getQuerryRunner().update(sql,dinnerTable.getTableName(),dinnerTable.getTableStatus(),dinnerTable.getOrderDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int tableId) {
        String sql = "delete from dinnerTable where id = ?";
        try {
            JdbcUtils.getQuerryRunner().update(sql,tableId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DinnerTable dinnerTable) {
        String sql = "update dinnerTable set tableName = ? , tableStatus = ? , orderDate = ? where id = ?";
        try {
            JdbcUtils.getQuerryRunner().update(sql,dinnerTable.getTableName(),dinnerTable.getTableStatus(),dinnerTable.getOrderDate(),dinnerTable.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> findAll() {
        String sql = "select * from dinnerTable";
        try {
            return JdbcUtils.getQuerryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> find(String tableName) {
        String sql = "select * from dinnerTable where tableName like ?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+tableName+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinnerTable where id = ?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql,new BeanHandler<DinnerTable>(DinnerTable.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> findByStatus(int status) {
        String sql = "select * from dinnerTable where tableStatus=?";
        try {
            return JdbcUtils.getQuerryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class),status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test(){
        DinnerTableDao dao = new DinnerTableDaoImpl();
        dao.add(new DinnerTable());
    }

}
