package com.arawn.lib.dao;

import com.arawn.lib.entity.Jar;
import com.arawn.lib.util.JdbcUtil_C3P0;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Jar包Dao
 * Created by ArawN on 2017/10/29.
 */
public class JarDao {

    private static Logger logger = Logger.getLogger(JarDao.class);

    /**
     * 根据Jar名称判断是否存在
     * @param name
     * @return
     */
    public boolean existByName(String name) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        String sql = "SELECT * FROM t_jar WHERE name=?";

        boolean exist = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exist = true;
            }
        } catch (Exception e) {
            logger.error("JarDao existByName ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
        return exist;
    }

    /**
     * 插入jar包
     * @param jar
     */
    public void insert(Jar jar) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        String sql = "INSERT INTO t_jar VALUES(NULL,?,?,?,?,0,0,0,0,NOW(),NOW())";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            preparedStatement.setString(2, jar.getName());
            preparedStatement.setString(3, jar.getPath());
            if (jar.getName().endsWith("javadoc.jar")) {
                preparedStatement.setString(4, "javadoc");
            } else if (jar.getName().endsWith("sources.jar")) {
                preparedStatement.setString(4, "sources");
            } else {
                preparedStatement.setString(4, "jar");
            }

            int result = preparedStatement.executeUpdate();
            logger.info("插入" + jar.getName() + " ==> result:" + result);
        } catch (Exception e) {
            logger.error("JarDao insert ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
    }

    /**
     * 根据条件查询jar包列表
     * @param param
     * @return
     */
    public List<Jar> listByParam(Jar param) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        StringBuilder sql = new StringBuilder("SELECT * FROM t_jar");
        if (param.getTagState() != null) {
            sql.append(" and tag_state=" + param.getTagState());
        }
        if (param.getIndexState() != null) {
            sql.append(" and index_state=" + param.getIndexState());
        }

        List<Jar> jarList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql.toString().replaceFirst("and", "where"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String jarId = resultSet.getString("jar_id");
                String name = resultSet.getString("name");

                Jar jar = new Jar();
                jar.setJarId(jarId);
                jar.setName(name);

                jarList.add(jar);
            }
        } catch (Exception e) {
            logger.error("JarDao list ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
        return jarList;
    }

    /**
     * 根据jarId更新生成标签状态
     * @param jarId
     */
    public void updateTagStateByJarId(String jarId) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        String sql = "UPDATE t_jar SET tag_state=1,update_time=NOW() WHERE jar_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, jarId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("JarDao updateTagStateByJarId ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
    }

    /**
     * 根据jarId更新生成索引状态
     * @param jarId
     */
    public void updateIndexStateByJarId(String jarId) {
        Connection connection = JdbcUtil_C3P0.getConnection();
        String sql = "UPDATE t_jar SET index_state=1,update_time=NOW() WHERE jar_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, jarId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("JarDao updateIndexStateByJarId ==>", e);
        } finally {
            JdbcUtil_C3P0.release(connection);
        }
    }

}
