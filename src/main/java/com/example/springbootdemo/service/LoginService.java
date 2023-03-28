package com.example.springbootdemo.service;

import com.example.springbootdemo.common.ResponseResult;
import com.example.springbootdemo.dao.User;
import org.springframework.stereotype.Service;

/**
 * @Author gaosen
 * @Date 2023/3/28 14:38
 * @Version 1.0
 */
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
