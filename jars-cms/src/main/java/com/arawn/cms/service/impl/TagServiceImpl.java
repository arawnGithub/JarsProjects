package com.arawn.cms.service.impl;

import com.arawn.cms.dao.TagDao;
import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 标签Service实现类
 * Created by ArawN on 2017/11/20.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource
    private TagDao tagDao;

    @Override
    public List<Tag> listByRand(Integer n) {
        return tagDao.listByRand(n);
    }

    @Override
    public List<Tag> listByMap(Map<String, Object> map) {
        return tagDao.listByMap(map);
    }

    @Override
    public Long countByMap(Map<String, Object> map) {
        return tagDao.countByMap(map);
    }

    @Override
    public int insert(Tag tag) {
        return tagDao.insert(tag);
    }

    @Override
    public int updateById(Tag tag) {
        return tagDao.updateById(tag);
    }

    @Override
    public int deleteById(Integer id) {
        return tagDao.deleteById(id);
    }
}
