package com.arawn.cache;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ArawN on 2017/10/29.
 */
public class JVMCache {

    /**
     * 排除的URL后缀
     */
    public static String[] excludeUrl = new String[]{".pom", ".xml", ".md5", ".sha1", ".asc", ".gz", ".zip", "../"};

    /**
     * 等待爬取的URL
     */
    public static Queue<String> waitForCrawlerUrls = new LinkedList<>();

    /**
     * 统计发现多少个目标(jar包)
     */
    public static int totalJar;
}
