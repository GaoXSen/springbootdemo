package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCOntroller {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
