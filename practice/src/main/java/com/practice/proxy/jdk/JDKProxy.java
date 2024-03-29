package com.practice.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDk动态代理实现
 * 基于接口的方式实现动态代理
 */
public class JDKProxy {

    private Object object;

    public JDKProxy() {
    }

    public JDKProxy(Object object) {
        this.object = object;
    }

    /**
     * 动态代理实现方法逻辑增强
     */
    public void process() {
        Class<?>[] interfaces = {JDKTarget.class};

        JDKTarget targetProxy = (JDKTarget) Proxy.newProxyInstance(getClass().getClassLoader(), interfaces, new InvocationHandler() {

            /**
             * @param proxy  代理对象
             * @param method 代理方法
             * @param args   方法参数列表
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("JDK 扩展代理前置逻辑实现！");
                System.out.println(proxy.getClass());
                // TODO 此处可自主新增逻辑
                Object result = method.invoke(object, args);
                System.out.println("JDK 扩展代理后置逻辑实现！");
                // TODO 此处可自主新增逻辑
                return result;
            }

        });

        final Object target = targetProxy.invokeMethod("target", true);
        System.out.println(target);
    }

}

