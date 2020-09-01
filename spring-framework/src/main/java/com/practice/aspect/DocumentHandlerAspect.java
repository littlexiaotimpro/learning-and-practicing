package com.practice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy //注解的方式开启AOP自动代理
public class DocumentHandlerAspect {

    @Pointcut(value = "execution(* com.practice.service.impl.DocumentHandlerServiceImpl.uploadExcel(..))")
    public void pointCut(){}

    /**
     * 前置通知
     */
    @Before(value = "pointCut()")
    public void before(){
        System.out.println("Before............前置通知");
    }

    /**
     * 后置通知
     */
    @AfterReturning(value = "pointCut()", returning = "res")
    public void afterReturning(Object res){
        // returning 接收的参数实际是由@Around返回
        System.out.println("AfterReturning......后置通知, returning = " + res);
    }

    /**
     * 环绕通知
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around......环绕前置通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("Around......环绕后置通知");
        return proceed;
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "pointCut()")
    public void afterThrowing(){
        System.out.println("AfterThrowing......异常通知");
    }

    /**
     * 最终通知
     */
    @After(value = "pointCut()")
    public void After(){
        System.out.println("After......最终通知");
    }

}
