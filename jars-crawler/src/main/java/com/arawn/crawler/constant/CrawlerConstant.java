package com.arawn.crawler.constant;

/**
 * 爬虫常量
 * Created by ArawN on 2017/12/2.
 */
public class CrawlerConstant {

    /**
     * 爬虫文件的路径
     */
    public static final String FILE_PATH = "D:\\JarsCrawler\\url.txt";

    /**
     * 排除的URL后缀
     */
    public static final String EXCLUDE_URL = "../";

    /**
     * 匹配URL地址（以/结尾）
     */
    public static final String REGEX = ".*/";

    /**
     * .jar
     */
    public static final String POINT_JAR = ".jar";

    /**
     * a标签
     */
    public static final String A_TAG = "a";

    /**
     * href属性
     */
    public static final String HREF_ATTR = "href";

    /**
     * 固定线程池数目
     */
    public static final Integer fixedThreadPoolCount = 10;
}
