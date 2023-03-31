package com.example.springbootdemo.security;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common.ResponseResult;
import com.example.springbootdemo.util.WebUtils;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author gaosen
 * @Date 2023/3/30 9:37
 * @Version 1.0
 */
//@Component
//public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
//        String json = JSON.toJSONString(result);
//        WebUtils.renderString(response, json);
//    }
//
//}
