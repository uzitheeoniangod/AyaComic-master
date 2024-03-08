package com.example.aya.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @RequestMapping("/helloo")
    public String index() {
        return "hello, world";
    }
}
