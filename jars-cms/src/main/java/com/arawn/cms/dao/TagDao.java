package com.arawn.cms.dao;

import com.arawn.cms.entity.Tag;

import java.util.List;

/**
 * 标签Dao接口
 * Created by ArawN on 2017/11/20.
 */
public interface TagDao {

    /**
     * 随机获取n个标签
     * @param n
     * @return
     */
    List<Tag> listByRand(Integer n);
}
