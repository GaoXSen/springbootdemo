package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.ResponseResult;
import com.example.springbootdemo.dao.User;
import com.example.springbootdemo.servcie.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gaosen
 * @Date 2023/3/28 14:36
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginServcie.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginServcie.logout();
    }

}
