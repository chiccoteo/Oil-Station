package com.sigma.oilstation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sigma.oilstation.utils.AppConstant.BASE_PATH;

@RequestMapping(BASE_PATH + "/limit")
public interface LimitController {

    @PutMapping
    HttpEntity<?> update(@RequestParam(name = "limit") Long limit);

    @GetMapping
    HttpEntity<?> get();

}
