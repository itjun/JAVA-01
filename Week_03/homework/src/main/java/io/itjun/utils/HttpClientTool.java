package io.itjun.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * http 请求工具
 */
public class HttpClientTool {

    public static String invoking(String method, String url) {
        if (method == null || "".equals(method)) {
            throw new RuntimeException("http method 不允许为空");
        }
        if (url == null || "".equals(url)) {
            throw new RuntimeException("http url 不允许为空");
        }
        CloseableHttpClient request = HttpClientBuilder.create().build();
        switch (method) {
            case "GET":
                return get(request, url);
            case "POST":
                return post(request, url);
            default:
                return null;
        }
    }

    public static String get(CloseableHttpClient client, String url) {
        System.out.println("实际访问地址为: " + url);
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(CloseableHttpClient client, String url) {
        return null;
    }

}
