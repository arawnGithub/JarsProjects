package com.arawn.crawler.main;

import com.arawn.crawler.cache.JVMCache;
import com.arawn.crawler.constant.CrawlerConstant;
import com.arawn.crawler.em.LogMessageEnum;
import com.arawn.crawler.util.StringUtil;
import com.arawn.lib.dao.JarDao;
import com.arawn.lib.entity.Jar;
import com.arawn.crawler.runnable.CrawlerRunnable;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 爬虫起始类
 * Created by ArawN on 2017/9/9.
 */
public class CrawlerMain {

    private static Logger logger = Logger.getLogger(CrawlerMain.class);

    /**
     * jar包Dao
     */
    private static JarDao jarDao = new JarDao();

    /**
     * main方法
     * @param args
     */
    public static void main(String[] args) {
        logger.info("开始执行爬虫任务");
        init();
    }

    /**
     * 初始化
     */
    public static void init() {
        logger.info("读取爬虫配置文件");

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(CrawlerConstant.FILE_PATH);
            bufferedReader = new BufferedReader(fileReader);

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                addUrl(line, LogMessageEnum.INIT.getDescription());
            }

        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error("IOException", e);
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error("IOException", e);
                }
            }
        }

        logger.info("完成读取爬虫配置文件");
        parseUrl();
    }

    /**
     * 添加url到爬虫队列，假如队列中存在，就不添加
     * @param realUrl
     */
    public static void addUrl(String realUrl, String info) {
        if (StringUtil.isEmpty(realUrl)) {
            return;
        }

        if (!JVMCache.waitForCrawlerUrls.contains(realUrl)) {
            JVMCache.waitForCrawlerUrls.add(realUrl);
            logger.info("[" + info + "]" + realUrl + "添加到爬虫队列");
        }
    }

    /**
     * 解析网页请求
     */
    public static void parseUrl() {
        ExecutorService executorService = Executors.newFixedThreadPool(CrawlerConstant.fixedThreadPoolCount);
        Runnable runnable = new CrawlerRunnable();

        boolean exeFlag = true;
        while (exeFlag) {
            if (JVMCache.waitForCrawlerUrls.size() > 0) {
                executorService.execute(runnable);
            } else {
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) { // 活动线程数量为0
                    executorService.shutdown(); // 结束所有线程
                    exeFlag = false;
                    logger.info("爬虫任务已经完成");
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
    }

    /**
     * 解析网页内容
     * @param webPageContent
     */
    public static void parseWebPage(String webPageContent, String prefixUrl) {
        Document doc = Jsoup.parse(webPageContent);
        Elements links = doc.select(CrawlerConstant.A_TAG); // 获取所有超链接元素

        for (Element link : links) {
            String url = link.attr(CrawlerConstant.HREF_ATTR);
            String realUrl = prefixUrl + url;

            if (CrawlerConstant.EXCLUDE_URL.equals(url)) {
                continue;
            }

            if (url.endsWith(CrawlerConstant.POINT_JAR)) { // 需要采集的URL
                JVMCache.totalJar++;
                logger.info("发现第" + JVMCache.totalJar + "目标：" + realUrl);

                addJar(url, realUrl);
            } else if (url.matches(CrawlerConstant.REGEX)) { // 需要继续爬取的URL
                logger.info("爬虫url队列新增url:" + realUrl);
                addUrl(realUrl, LogMessageEnum.PARSE_PAGE.getDescription());
            }
        }
    }

    /**
     * 添加Jar包信息
     * @param url
     * @param realUrl
     */
    public static void addJar(String url, String realUrl) {
        boolean exist = jarDao.existByName(url);
        if (exist) {
            logger.info("[" + url + "]数据库已存在");
            return;
        }

        Jar jar = new Jar();
        jar.setName(url);
        jar.setPath(realUrl);

        jarDao.insert(jar);
    }

}