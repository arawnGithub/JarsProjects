package com.arawn.crawler;

import com.arawn.cache.JVMCache;
import com.arawn.dao.JarDao;
import com.arawn.entity.Jar;
import com.arawn.runnable.CrawlerRunnable;
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
public class StartCrawler {

    private static Logger logger = Logger.getLogger(StartCrawler.class);

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
            fileReader = new FileReader("D:\\JarsCrawler\\url.txt");
            bufferedReader = new BufferedReader(fileReader);

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                addUrl(line, "初始化");
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
        if (realUrl == null || "".equals(realUrl)) {
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
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable runnable = new CrawlerRunnable();

        boolean exeFlag = true;
        while (exeFlag) {
            if (JVMCache.waitForCrawlerUrls.size() > 0) {
                executorService.execute(runnable);
            } else {
                // 活动线程数量为0
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) {
                    executorService.shutdown(); // 结束所有线程
                    exeFlag = false;
                    logger.info("爬虫任务已经完成");
                }
            }

            try {
                Thread.sleep(1000);
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
        if (webPageContent == null || "".equals(webPageContent)) {
            return;
        }

        Document doc = Jsoup.parse(webPageContent);
        Elements links = doc.select("a"); // 获取所有超链接元素
        for (Element link : links) {
            String url = link.attr("href");
            String realUrl = prefixUrl + url;

            if (excludeUrl(url)) {
                continue;
            }

            if (url.endsWith(".jar")) { // 需要采集的URL
                JVMCache.totalJar++;
                logger.info("发现第" + JVMCache.totalJar + "目标：" + realUrl);

                addJar(url, realUrl);
            } else { // 需要继续爬取的URL
                logger.info("爬虫url队列新增url:" + realUrl);
                addUrl(realUrl, "解析网页");
            }
        }
    }

    /**
     * 判断是否是排除的url
     * @param url
     * @return
     */
    public static boolean excludeUrl(String url) {
        boolean exclude = false;
        for (int i = 0; i < JVMCache.excludeUrl.length; i++) {
            if (url.endsWith(JVMCache.excludeUrl[i])) {
                exclude = true;
                break;
            }
        }

        return exclude;
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