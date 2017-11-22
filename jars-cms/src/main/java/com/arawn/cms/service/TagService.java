package com.arawn.cms.service;

import com.arawn.cms.entity.Tag;

import java.util.List;

/**
 * 标签Service接口
 * Created by ArawN on 2017/11/20.
 */
public interface TagService {

    /**
     * 随机获取n个标签
     * @param n
     * @return
     */
    List<Tag> listByRandom(Integer n);
}
