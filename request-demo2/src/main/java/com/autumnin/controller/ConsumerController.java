package com.autumnin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://localhost:8081";

    private WebClient webClient = WebClient.builder().baseUrl(SERVICE_URL).build();

    @GetMapping("/webClientTest")
    public String webClientTest() {
        Mono<String> mono = webClient.get()
                .uri("/hello")//请求地址
                .retrieve()// 获取响应结果
                .bodyToMono(String.class);
        // 打印请求结果
        mono.subscribe(result -> {
            System.out.println(result);
        });

        return "request success";
    }

}
