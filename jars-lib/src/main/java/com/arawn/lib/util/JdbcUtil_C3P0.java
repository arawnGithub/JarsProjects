package com.arawn.lib.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0工具类
 * Created by ArawN on 2017/10/31.
 */
public class JdbcUtil_C3P0 {

    private static Logger logger = Logger.getLogger(JdbcUtil_C3P0.class);

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
     * 关闭数据库连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

}	
