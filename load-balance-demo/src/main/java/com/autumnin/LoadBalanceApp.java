package com.autumnin;

import com.autumnin.config.LoadBalanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication
@LoadBalancerClient(value = "goods-service", configuration = LoadBalanceConfig.class)
@EnableDiscoveryClient
public class LoadBalanceApp {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignDemo.class);
    }
}
