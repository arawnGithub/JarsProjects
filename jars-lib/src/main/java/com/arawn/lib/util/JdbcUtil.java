package com.arawn.lib.util;

import com.arawn.lib.constant.DBConstant;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Jdbc工具类
 * Created by ArawN on 2017/9/10.
 */
public class JdbcUtil {

    private static Logger logger = Logger.getLogger(JdbcUtil.class);

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public static Connection getCon() {
        Connection connection = null;

        try {
            Class.forName(DBConstant.JDBC_NAME);
            connection = DriverManager.getConnection(
                    DBConstant.URL, DBConstant.USERNAME, DBConstant.PASSWORD);
        } catch (Exception e) {
            logger.error("获取数据库连接失败", e);
        }
        return connection;
    }

}