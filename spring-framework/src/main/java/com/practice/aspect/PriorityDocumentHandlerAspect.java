package com.practice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 当同一个切入点存在多个切面类时
 * 默认顺序为按优先级较高的通知排序
 */
@Aspect
@Component
@Order(1)
public class PriorityDocumentHandlerAspect {

    @Pointcut(value = "execution(* com.practice.service.impl.DocumentHandlerServiceImpl.uploadExcel(..))")
    public void pointCut(){}

    /**
     * 前置通知
     */
    @Before(value = "pointCut()")
    public void before(){
        System.out.println("Before............优先执行的前置通知");
    }

    /**
     * 后置通知
     */
    @AfterReturning(value = "pointCut()", returning = "res")
    public void afterReturning(Object res){
        // returning 接收的参数实际是由@Around返回
        System.out.println("AfterReturning......优先执行的后置通知, returning = " + res);
    }

    /**
     * 环绕通知
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around......优先执行的环绕前置通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("Around......优先执行的环绕后置通知");
        return proceed;
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(Exception e){
        System.out.println("AfterThrowing......优先执行的异常通知："+e.getMessage());
    }

    /**
     * 最终通知
     */
    @After(value = "pointCut()")
    public void After(){
        System.out.println("After......优先执行的最终通知");
    }

}
