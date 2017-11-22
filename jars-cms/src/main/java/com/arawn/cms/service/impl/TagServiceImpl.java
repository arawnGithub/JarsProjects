package com.arawn.cms.service.impl;

import com.arawn.cms.dao.TagDao;
import com.arawn.cms.entity.Tag;
import com.arawn.cms.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
