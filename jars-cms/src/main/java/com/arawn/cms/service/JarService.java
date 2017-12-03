package com.arawn.cms.service;

import com.arawn.cms.entity.Jar;

import java.util.List;
import java.util.Map;

/**
 * Jar包Service接口
 * Created by ArawN on 2017/12/3.
 */
public interface JarService {

    /**
     * 根据jarId查询
     * @param jarId
     * @return
     */
    Jar queryByJarId(String jarId);

    /**
     * 根据jarId修改Jar包信息
     * @param jar
     * @return
     */
    int updateByJarId(Jar jar);

    /**
     * 根据条件查询列表
     * @param map
     * @return
     */
    List<Jar> listByMap(Map<String, Object> map);

    /**
     * 根据条件查询记录数
     * @param map
     * @return
     */
    Long countByMap(Map<String, Object> map);
}
