package com.autumnin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    private final String SERVICE_URL = "http://goods-service";

    @GetMapping("serviceTest")
    public String serviceTest() {
        return restTemplate.getForObject(SERVICE_URL + "/serviceTest", String.class);
    }
}
