package com.arawn.cms.service;

import com.arawn.cms.entity.Manager;

/**
 * 管理员Service接口
 * Created by ArawN on 2017/11/18.
 */
public interface ManagerService {

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
