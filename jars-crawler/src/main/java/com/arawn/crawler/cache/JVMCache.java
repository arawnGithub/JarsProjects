package com.arawn.crawler.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 模拟缓存
 * Created by ArawN on 2017/10/29.
 */
public class JVMCache {

    /**
     * 等待爬取的URL
     */
    public static BlockingQueue<String> waitForCrawlerUrls = new LinkedBlockingQueue<>();

    /**
     * 统计发现多少个目标(jar包)
     */
    public static Long totalJar = 0L;
}
