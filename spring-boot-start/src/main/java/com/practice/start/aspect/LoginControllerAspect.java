package com.practice.start.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author chen.hong
 * @date 2022/3/31
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class LoginControllerAspect {

    @Pointcut(value = "execution(* com.practice.start.controller.StockController.*(..))")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around......环绕前置通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("Around......环绕后置通知");
        return proceed;
    }

}
