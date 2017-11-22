package com.arawn.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 标签实体
 * Created by ArawN on 2017/11/20.
 */
@Getter
@Setter
public class Tag {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String name;
}
