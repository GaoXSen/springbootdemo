package com.example.springbootdemo.util.aoputil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author gaosen
 * @since 2024/9/10 10:06
 */

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(logExecutionTime)")  // 切点：拦截带有 @LogExecutionTime 注解的方法
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        Long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        Long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }

}
