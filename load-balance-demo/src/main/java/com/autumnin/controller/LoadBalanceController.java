package com.autumnin.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class LoadBalanceController {
    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICE_URL = "http://goods-service";

    @GetMapping("/balance")
    public String serviceTest() {
        return restTemplate.getForObject(SERVICE_URL + "/serviceTest", String.class);
    }
}
