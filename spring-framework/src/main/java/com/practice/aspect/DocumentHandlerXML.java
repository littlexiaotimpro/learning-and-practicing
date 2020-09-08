package com.practice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;


public class DocumentHandlerXML {


    public void pointCut(){}

    /**
     * 前置通知
     */
    public void before(){
        System.out.println("Before............前置通知");
    }

    /**
     * 后置通知
     */
    public void afterReturning(Object res){
        // returning 接收的参数实际是由@Around返回
        System.out.println("AfterReturning......后置通知, returning = " + res);
    }

    /**
     * 环绕通知
     */
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around......环绕前置通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("Around......环绕后置通知");
        return proceed;
    }

    /**
     * 异常通知
     */
    public void afterThrowing(Exception e){
        try {
            throw e;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("AfterThrowing......异常通知");
    }

    /**
     * 最终通知
     */
    public void After(){
        System.out.println("After......最终通知");
    }

}
