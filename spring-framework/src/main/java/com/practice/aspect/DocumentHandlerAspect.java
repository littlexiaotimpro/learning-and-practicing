package com.practice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy //注解的方式开启AOP自动代理
public class DocumentHandlerAspect {

    @Pointcut(value = "execution(* com.practice.service.impl.DocumentHandlerServiceImpl.uploadExcel(..))")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void before(){
        System.out.println("Before......");
    }


}
