package com.arawn.entity;

import java.util.Date;

/**
 * Created by ArawN on 2017/10/29.
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJarId() {
        return jarId;
    }

    public void setJarId(String jarId) {
        this.jarId = jarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getDownHit() {
        return downHit;
    }

    public void setDownHit(Integer downHit) {
        this.downHit = downHit;
    }

    public Integer getIndexState() {
        return indexState;
    }

    public void setIndexState(Integer indexState) {
        this.indexState = indexState;
    }

    public Integer getTagState() {
        return tagState;
    }

    public void setTagState(Integer tagState) {
        this.tagState = tagState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
