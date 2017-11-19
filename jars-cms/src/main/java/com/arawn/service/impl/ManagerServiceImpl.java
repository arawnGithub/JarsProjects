package com.arawn.service.impl;

import com.arawn.dao.ManagerDao;
import com.arawn.entity.Manager;
import com.arawn.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员Service实现类
 * Created by ArawN on 2017/11/18.
 */
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerDao managerDao;

    @Override
    public Manager queryByUsername(String username) {
        return managerDao.queryByUsername(username);
    }
}
