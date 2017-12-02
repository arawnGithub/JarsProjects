package com.arawn.lib.dao;

import com.arawn.lib.entity.Tag;
import com.arawn.lib.util.JdbcUtil_C3P0;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 标签Dao
 * Created by ArawN on 2017/10/29.
 */
public class TagDao {

    private static Logger logger = Logger.getLogger(TagDao.class);

    /**
     * 根据标签名称判断是否存在
     * @param name
     * @return
     */
    public boolean existByName(String name) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        String sql = "SELECT * FROM t_tag WHERE name=?";

        boolean exist = true;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                exist = false;
            }
        } catch (Exception e) {
            logger.error("TagDao existByName ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
        return exist;
    }

    /**
     * 插入标签
     * @param tag
     */
    public void insert(Tag tag) {
        Connection connection = JdbcUtil_C3P0.getConnection();

        try {
            String sql = "INSERT INTO t_tag VALUES(NULL,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tag.getName());

            int result = preparedStatement.executeUpdate();
            logger.info("插入" + tag.getName() + "标签 ==> result:" + result);
        } catch (Exception e) {
            logger.error("TagDao insert ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
    }

}
