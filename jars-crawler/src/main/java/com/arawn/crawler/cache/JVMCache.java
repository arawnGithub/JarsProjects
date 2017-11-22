package com.arawn.crawler.cache;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ArawN on 2017/10/29.
 */
public class JVMCache {

    /**
     * 排除的URL后缀
     */
    public static String excludeUrl = "../";

    /**
     * 匹配URL地址
     */
    public static String regex = ".*/";

    /**
     * 等待爬取的URL
     */
    public static Queue<String> waitForCrawlerUrls = new LinkedList<>();

    /**
     * 统计发现多少个目标(jar包)
     */
    public static Long totalJar = 0L;
}
