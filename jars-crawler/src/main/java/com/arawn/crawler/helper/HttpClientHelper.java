package com.arawn.crawler.helper;

import com.arawn.crawler.em.LogMessageEnum;
import com.arawn.crawler.main.CrawlerMain;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * HttpClient帮助类
 * Created by ArawN on 2017/10/31.
 */
public class HttpClientHelper {

    private static Logger logger = Logger.getLogger(HttpClientHelper.class);

    /**
     * utf-8字符编码
     */
    private static final String CHARSET_UTF_8 = "utf-8";

    /**
     * 发送Get请求
     * @param url
     * @return
     */
    public static String sendHttpGet(String url) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(100000) // 设置读取超时时间
                .setConnectTimeout(5000) // 设置连接超时时间
                .build();

        // 创建httpClient和httpGet实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        String responseContent = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            CrawlerMain.addUrl(url, LogMessageEnum.EXCEPTION.getDescription());
        } catch (IOException e) {
            logger.error(e.getMessage());
            CrawlerMain.addUrl(url, LogMessageEnum.EXCEPTION.getDescription());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return responseContent;
    }

}