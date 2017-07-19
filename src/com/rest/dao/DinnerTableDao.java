package com.rest.dao;

import com.rest.domain.DinnerTable;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xuero on 2017/7/13.
 */
public interface DinnerTableDao {
    void add(DinnerTable dinnerTable);

    void delete(int tableId);

    void update(DinnerTable dinnerTable);


    List<DinnerTable> findAll();

    List<DinnerTable> find(String tableName);

    DinnerTable findById(int id);

    List<DinnerTable> findByStatus(int status);
}
