package com.rest.service.impl;

import com.rest.dao.DinnerTableDao;
import com.rest.domain.DinnerTable;
import com.rest.service.DinnerTableService;
import com.rest.utils.WebFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xuero on 2017/7/14.
 */
public class DinnerTableServiceImpl implements DinnerTableService {

    DinnerTableDao dao = WebFactory.getInstance("DinnerTableDao",DinnerTableDao.class);

    @Override
    public void addTable(DinnerTable dinnerTable) {
        try {
            dao.add(dinnerTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTable(int tableId) {
        try {
            dao.delete(tableId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTable(DinnerTable dinnerTable) {
        try {
            dao.update(dinnerTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> getAllTables() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> getSpecificTables(String tableName) {
        try {

            return dao.find(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable getTableById(int tableId) {
        try {
            return dao.findById(tableId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reserveTable(int tableId,String date) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = dateFormat.parse(date);
            DinnerTable table = dao.findById(tableId);

            if(table != null && table.getTableStatus() == 1 && date1 != null){
                DinnerTable dinnerTable = new DinnerTable();
                dinnerTable.setId(tableId);
                dinnerTable.setTableName(table.getTableName());
                dinnerTable.setTableStatus(2);
                dinnerTable.setOrderDate(date1);
                dao.update(dinnerTable);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelTable(int tableId) {
        try {
            DinnerTable table = dao.findById(tableId);
            if(table != null && table.getTableStatus() == 2){
                DinnerTable dinnerTable = new DinnerTable();
                dinnerTable.setId(tableId);
                dinnerTable.setTableName(table.getTableName());
                dinnerTable.setTableStatus(1);
                dinnerTable.setOrderDate(null);
                dao.update(dinnerTable);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> getIdleTables() {
        try {
            return dao.findByStatus(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
