package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.user.impl.AopTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gaosen
 * @since 2024/9/10 10:37
 */
@Controller
@RequestMapping("/aop")
public class AopTestController {

    @Autowired
    private AopTestService aopTestService;

    @GetMapping("/test")
    public String serve() {
        aopTestService.serve();
        return "aop";
    }

}
