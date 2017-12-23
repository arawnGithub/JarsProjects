package com.arawn.crawler.helper;

import com.arawn.crawler.constant.HttpConstant;
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
     * 初始化HttpClient实例
     */
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * 发送Get请求
     * @param url
     * @return
     */
    public static String sendHttpGet(String url) {
        // 创建HttpGet实例
        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(HttpConstant.SOCKET_TIMEOUT) // 设置读取超时时间
                .setConnectTimeout(HttpConstant.CONNECT_TIMEOUT) // 设置连接超时时间
                .build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        String responseContent = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, HttpConstant.CHARSET);
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