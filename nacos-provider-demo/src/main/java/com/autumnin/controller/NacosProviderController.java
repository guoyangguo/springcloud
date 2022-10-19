package com.autumnin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class NacosProviderController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/serviceTest")
    public String serviceTest() {
        return "this is service from port" + serverPort;
    }

    @GetMapping("/{goodId}")
    public String getGood(@PathVariable int goodId) {
        return "goodId is " + goodId;
    }
}
