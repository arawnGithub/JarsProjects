package com.arawn.cms.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Md5工具类
 * Created by ArawN on 2017/11/18.
 */
public class Md5Utils {

    /**
     * Md5盐
     */
    public static final String SALT = "arawn";

    /**
     * Md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }
}
