package com.autumnin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "goods-service", path = "/good")
public interface GoodService {
    @GetMapping(value = "/{goodId}")
    String getGood(@PathVariable(value = "goodId") int goodId);
}
