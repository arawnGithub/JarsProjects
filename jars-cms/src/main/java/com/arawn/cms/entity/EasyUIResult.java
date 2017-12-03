package com.arawn.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * EasyUI列表返回实体
 * Created by ArawN on 2017/12/3.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EasyUIResult<T> {

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 总记录数
     */
    private Long total;
}
