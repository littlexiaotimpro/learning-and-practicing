package com.practice.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib动态代理
 * 基于非接口的代理实现
 */
public class CGLibProxy {

    public void process() {
        // MethodInterceptor 方法拦截接口，父类的所有方法都会加上此拦截逻辑
        CGLibTarget target = (CGLibTarget) Enhancer.create(CGLibTarget.class, null, new MethodInterceptor() {
            /**
             * @param o           被代理对象
             * @param method      被代理方法
             * @param objects     方法参数列表
             * @param methodProxy 方法代理对象
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("CGLIB 扩展代理前置逻辑实现！");
                // TODO 此处可自主新增逻辑
                Object result = methodProxy.invokeSuper(o, objects);
                // 如下的方法会陷入死循环
//                Object result = method.invoke(o, objects);
                System.out.println("CGLIB 扩展代理后置逻辑实现！");
                // TODO 此处可自主新增逻辑

                // TODO 可以修改方法的返回结果
                return result;
            }
        });
        final Object o = target.invokeMethod("a", "b");
        System.out.println(o);
    }

}
