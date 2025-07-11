package com.example.springbootdemo.service.user.impl;

import com.example.springbootdemo.util.aoputil.LogExecutionTime;
import org.springframework.stereotype.Service;

/**
 * @author gaosen
 * @since 2024/9/10 10:24
 */
@Service
public class AopTestService {
    @LogExecutionTime
    public void serve() {
        System.out.println("开始Aop服务测试");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
