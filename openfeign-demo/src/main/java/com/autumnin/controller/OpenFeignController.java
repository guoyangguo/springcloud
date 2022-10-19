package com.autumnin.controller;

import com.autumnin.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/good/{id}")
    public String getGood(@PathVariable int id) {
        return goodService.getGood(id);
    }
}
