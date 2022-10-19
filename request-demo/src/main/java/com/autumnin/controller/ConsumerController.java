package com.autumnin.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://localhost:8081";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/httpTest")
    public String httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 判断返回码
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "request success";
    }

    @GetMapping("/restTest")
    public String requestTemplate() {
        String str = restTemplate.getForObject(SERVICE_URL + "/hello", String.class);
        System.out.println(str);
        return "request success";
    }
}
