package com.autumnin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 开启OpenFeign 对指定包下的起作用
@EnableFeignClients(basePackages = {"com.autumnin"})
public class OpenFeignApp {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApp.class);
    }
}
