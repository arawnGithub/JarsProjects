package com.arawn.cms.dao;

import com.arawn.cms.entity.Manager;

/**
 * 管理员Dao接口
 * Created by ArawN on 2017/11/18.
 */
public interface ManagerDao {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Manager queryByUsername(String username);

    /**
     * 更新管理员信息
     * @param manager
     * @return
     */
    int updateById(Manager manager);
}
