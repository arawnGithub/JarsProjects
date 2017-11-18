package com.arawn.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ArawN on 2017/10/29.
 */
@Getter
@Setter
public class Jar {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * jar包ID
     */
    private String jarId;

    /**
     * jar包名称
     */
    private String name;

    /**
     * jar包地址
     */
    private String path;

    /**
     * jar包类型
     */
    private String type;

    /**
     * 点击次数
     */
    private Integer click;

    /**
     * 下载次数
     */
    private Integer downHit;

    /**
     * 是否生成索引 0-否 1-是
     */
    private Integer indexState;

    /**
     * 是否生成标签 0-否 1-是
     */
    private Integer tagState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
