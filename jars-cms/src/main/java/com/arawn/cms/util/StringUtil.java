package com.arawn.cms.util;

import java.util.UUID;

/**
 * 字符串工具类
 * Created by ArawN on 2017/10/29.
 */
public class StringUtil {

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 格式化模糊查询
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        }
        return null;
    }

    /**
     * 生成JarId
     * @return
     */
    public static String genJarId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}