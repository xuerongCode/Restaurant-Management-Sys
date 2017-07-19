package com.rest.service;


import com.rest.domain.DinnerTable;

import java.util.List;

/**
 * Created by xuero on 2017/7/14.
 */
public interface DinnerTableService {

    void addTable(DinnerTable dinnerTable);
    void deleteTable(int tableId);
    void updateTable(DinnerTable dinnerTable);
    List<DinnerTable> getAllTables();
    List<DinnerTable> getSpecificTables(String tableName);
    DinnerTable getTableById(int tableId);
    void reserveTable(int tableId,String date);
    void cancelTable(int tableId);
    List<DinnerTable> getIdleTables();
}
