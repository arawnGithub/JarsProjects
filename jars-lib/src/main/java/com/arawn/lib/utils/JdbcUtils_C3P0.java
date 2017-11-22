package com.arawn.lib.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * c3p0工具类
 * Created by ArawN on 2017/10/31.
 */
public class JdbcUtils_C3P0 {

    private static Logger logger = Logger.getLogger(JdbcUtils_C3P0.class);

    private static ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    /**
     * 释放数据库连接
     *
     * @param conn
     */
    public static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

}	
