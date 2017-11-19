package com.arawn.service;

import com.arawn.entity.Manager;

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
}
