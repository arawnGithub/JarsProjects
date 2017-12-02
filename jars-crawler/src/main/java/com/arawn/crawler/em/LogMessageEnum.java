package com.arawn.crawler.em;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志消息枚举
 * Created by ArawN on 2017/12/2.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LogMessageEnum {

    INIT("初始化"), PARSE_PAGE("解析网页"), EXCEPTION("由于异常");

    private String description;
}
