package com.example.serviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("config")
@RestController
public class ConfigController {

    @Value("${max.text:'max'}")
    private String text;


    @GetMapping("/get")
    public String get(){
        return text;
    }


}
