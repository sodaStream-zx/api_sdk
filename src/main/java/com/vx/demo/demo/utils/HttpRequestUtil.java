package com.vx.demo.demo.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author TJ2020-6
 * @desc
 * @createTime 2020-04-10-11:16
 */
public class HttpRequestUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String httpPost(String paramJsonObject, String url) {

        String resp = "";//响应
        // 构造httprequest设置
        CloseableHttpClient httpClient = null;
        HttpPost postMethod;
        HttpResponse response;
        try {
            httpClient = HttpClients.createDefault();
            postMethod = new HttpPost(url);//传入URL地址
            //设置请求头
            postMethod.addHeader("Content-type", "application/json; charset=utf-8");
            postMethod.addHeader("X-Authorization", "");
            postMethod.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            postMethod.setEntity(new StringEntity(paramJsonObject, StandardCharsets.UTF_8));

            response = httpClient.execute(postMethod);
            //获取响应
            resp = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.warn("resp:" + resp);
            httpClient.close();
        } catch (Exception e) {
            log.error("发送POST请求出现异常！{}", e);
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException ex) {
                log.error("关闭httpClient异常 {}", ex);
                ex.printStackTrace();
            }
        }
        return resp;
    }

    /**
     * 发送get请求
     */
    public static String httpGet(String getRequest) {
        HttpResponse response;
        CloseableHttpClient client = null;
        StringBuilder result = new StringBuilder();
        try {
            // 构造httpRequest设置
            client = HttpClients.createDefault();
            HttpGet request = new HttpGet(getRequest);
            response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            log.warn("resultxx:" + result);
        } catch (Exception e) {
            log.error("发送GET请求出现异常！{} ", e);
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.error("关闭httpClient异常 {}", e);
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
