package com.arawn.crawler.runnable;

import com.arawn.crawler.cache.JVMCache;
import com.arawn.crawler.main.StartCrawler;
import com.arawn.crawler.helper.HttpClientHelper;
import org.apache.log4j.Logger;

/**
 * Created by ArawN on 2017/10/29.
 */
public class CrawlerRunnable implements Runnable {

    private static Logger logger = Logger.getLogger(CrawlerRunnable.class);

    public void run() {
        String url = JVMCache.waitForCrawlerUrls.poll(); // 第一个元素出队列，先进先出
        if (url == null || "".equals(url)) {
            return;
        }
        logger.info("执行解析url:" + url);

        String webPageContent = HttpClientHelper.sendHttpGet(url);
        StartCrawler.parseWebPage(webPageContent, url);
    }

}
