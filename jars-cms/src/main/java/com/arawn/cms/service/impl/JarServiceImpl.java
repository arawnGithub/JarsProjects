package com.arawn.cms.service.impl;

import com.arawn.cms.dao.JarDao;
import com.arawn.cms.entity.Jar;
import com.arawn.cms.service.JarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Jar包Service实现类
 * Created by ArawN on 2017/12/3.
 */
@Service("jarService")
public class JarServiceImpl implements JarService {

    @Resource
    private JarDao jarDao;

    @Override
    public Jar queryByJarId(String jarId) {
        return jarDao.queryByJarId(jarId);
    }

    @Override
    public int updateByJarId(Jar jar) {
        return jarDao.updateByJarId(jar);
    }

    @Override
    public List<Jar> listByMap(Map<String, Object> map) {
        return jarDao.listByMap(map);
    }

    @Override
    public Long countByMap(Map<String, Object> map) {
        return jarDao.countByMap(map);
    }
}
