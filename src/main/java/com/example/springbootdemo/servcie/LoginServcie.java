package com.example.springbootdemo.servcie;

import com.example.springbootdemo.common.ResponseResult;
import com.example.springbootdemo.dao.User;

/**
 * @Author gaosen
 * @Date 2023/3/29 10:33
 * @Version 1.0
 */
public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();
}
