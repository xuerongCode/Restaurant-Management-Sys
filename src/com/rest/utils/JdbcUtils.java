package com.rest.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * Created by xuero on 2017/7/3.
 */
public class JdbcUtils {

    private static DataSource dataSource;
    static {
            dataSource = new ComboPooledDataSource();
    }

    public static QueryRunner getQuerryRunner(){
        return new QueryRunner(dataSource);
    }

}
