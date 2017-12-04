package com.arawn.cms.service;

import com.arawn.cms.entity.Tag;

import java.util.List;
import java.util.Map;

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
    List<Tag> listByRand(Integer n);

    /**
     * 根据条件查询列表
     * @param map
     * @return
     */
    List<Tag> listByMap(Map<String, Object> map);

    /**
     * 根据条件查询记录数
     * @param map
     * @return
     */
    Long countByMap(Map<String, Object> map);

    /**
     * 添加标签
     * @param tag
     * @return
     */
    int insert(Tag tag);

    /**
     * 根据id修改标签
     * @param tag
     * @return
     */
    int updateById(Tag tag);

    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
