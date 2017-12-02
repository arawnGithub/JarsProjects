package com.arawn.crawler.runnable;

import com.arawn.crawler.cache.JVMCache;
import com.arawn.crawler.em.LogMessageEnum;
import com.arawn.crawler.main.CrawlerMain;
import com.arawn.crawler.helper.HttpClientHelper;
import com.arawn.crawler.util.StringUtil;
import org.apache.log4j.Logger;

/**
 * 爬虫任务类
 * Created by ArawN on 2017/10/29.
 */
public class CrawlerRunnable implements Runnable {

    private static Logger logger = Logger.getLogger(CrawlerRunnable.class);

    public void run() {
        String url = JVMCache.waitForCrawlerUrls.poll(); // 第一个元素出队列，先进先出
        if (StringUtil.isEmpty(url)) {
            return;
        }
        logger.info("执行解析url:" + url);

        String webPageContent = HttpClientHelper.sendHttpGet(url);
        if (StringUtil.isEmpty(webPageContent)) {
            CrawlerMain.addUrl(url, LogMessageEnum.EXCEPTION.getDescription());
        } else {
            CrawlerMain.parseWebPage(webPageContent, url);
        }
    }

}
