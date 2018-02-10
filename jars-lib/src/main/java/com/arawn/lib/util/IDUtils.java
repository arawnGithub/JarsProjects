package com.arawn.lib.util;

import java.util.Random;

/**
 * ID生成工具类
 * Created by ArawN on 2018/2/10.
 */
public class IDUtils {

    /**
     * jar包id生成
     */
    public static Long genJarId() {
        // 取当前时间的长整形值（包含毫秒）
        long millis = System.currentTimeMillis();
        // long millis = System.nanoTime();
        // 加上两位随机数
        Random random = new Random();
        int randomNum = random.nextInt(99);
        // 如果不足两位前面补0
        String str = millis + String.format("%02d", randomNum);
        return Long.valueOf(str);
    }

}