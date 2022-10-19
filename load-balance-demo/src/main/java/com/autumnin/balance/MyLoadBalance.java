package com.autumnin.balance;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLoadBalance implements ReactorLoadBalancer {
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSuppliers;
    private String serviceName;

    private AtomicInteger counter = new AtomicInteger(0);
    private AtomicInteger currentIndex = new AtomicInteger(0);

    public MyLoadBalance(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSuppliers, String serviceName) {
        this.serviceInstanceListSuppliers = serviceInstanceListSuppliers;
        this.serviceName = serviceName;
    }

    @Override
    public Mono<Response> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSuppliers.getIfAvailable();
        return supplier.get().next().map(this::getInstanceResponse);
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        ServiceInstance serviceInstance = null;
        if (instances.isEmpty()) {
            System.out.println("注册中心无可用实例" + serviceName);
            return new EmptyResponse();
        }
        int requestNum = counter.incrementAndGet();
        // 自定义负载方式
        if (requestNum < 2) {
            serviceInstance = instances.get(currentIndex.get());
        } else {
            // 重置请求次数
            counter = new AtomicInteger(0);
            if (currentIndex.get() >= instances.size()) {
                currentIndex = new AtomicInteger(0);
                serviceInstance = instances.get(instances.size() - 1);
                return new DefaultResponse(serviceInstance);
            }
            // 从可用的实例中获取
            serviceInstance = instances.get(currentIndex.get() - 1);
        }
        return new DefaultResponse(serviceInstance);
    }

    @Override
    public Mono<Response> choose() {
        return ReactorLoadBalancer.super.choose();
    }
}
