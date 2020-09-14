package com.practice.aspect;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP：
 * 1.在初始化对象之前，
 *  1.1.Spring 自动决定使用哪种代理模式
 *      CgLib动态代理:
 *          {@link org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept}CglibAopProxy
 *      JDK动态代理
 *          {@link org.springframework.aop.framework.JdkDynamicAopProxy#invoke}JdkDynamicAopProxy
 *  1.2.获取当前目标增强类型的增强方法，即获取增强通知的拦截器链
 *      List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *      若当前方法不存在增强通知的拦截器链，则直接跳过 MethodInvocation 对象的创建，直接调用目标方法
 *      若存在增强通知的拦截器链，则创建 MethodInvocation 的对应的方法调用对象，通过反射的方式执行方法
 * 2.{@link org.springframework.aop.framework.ReflectiveMethodInvocation#proceed}
 *      通过方法映射调用，调用当前代理对象的: ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
 * 3.按照通知的拦截器链的顺序依次执行，默认顺序如下：
 *      3.1.公开调用拦截器：{@link ExposeInvocationInterceptor#invoke(MethodInvocation)}
 *          将创建的方法调用对象，存在 ThreadLocal 类型遍历中进行缓存，然后开始执行 mi.proceed()
 *      3.2.异常通知拦截器：{@link AspectJAfterThrowingAdvice#invoke(MethodInvocation)}
 *      3.3.正常执行后置通知拦截器：{@link AfterReturningAdviceInterceptor#invoke(MethodInvocation)}
 *      3.4.最终通知拦截器：{@link AspectJAfterAdvice#invoke(MethodInvocation)}
 *      3.5.环绕通知拦截器：{@link AspectJAroundAdvice#invoke(MethodInvocation)}
 *          环绕通知比较特殊，它是先执行的目标方法的环绕前置逻辑
 *          然后调用 {@link ProceedingJoinPoint#proceed()}，继续执行之后的拦截器
 *          后续拦截器正常执行完成之后，执行环绕后置逻辑，然后按正常顺序执行后续的拦截逻辑
 *          若存在异常，则直接跳过环绕后置逻辑（正常的后置通知拦截器逻辑也跳过），执行后续的逻辑拦截逻辑
 *      3.6.前置通知拦截器：{@link MethodBeforeAdviceInterceptor#invoke(MethodInvocation)}
 *          先执行前置通知方法：this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
 *          再执行：return mi.proceed();
 *          <code>
 *              {
 *                  // this.currentInterceptorIndex 当前拦截器索引，从-1开始，使用前先自增
 *                  // 当执行到 前置通知时，此时的拦截器索引正好等于总拦截器数组数 -1，此时执行切入点目标方法
 * 		            if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
 * 			            return invokeJoinpoint();
 *                  }
 *                  Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
 *              }
 *          </code>
 */
@Aspect
@Component
@Order(2)
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
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(Exception e){
        System.out.println("AfterThrowing......异常通知："+e.getMessage());
    }

    /**
     * 最终通知
     */
    @After(value = "pointCut()")
    public void After(){
        System.out.println("After......最终通知");
    }

}
