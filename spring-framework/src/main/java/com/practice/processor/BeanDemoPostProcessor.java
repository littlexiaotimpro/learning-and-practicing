package com.practice.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 实现Bean对象的后置处理接口
 */
public class BeanDemoPostProcessor implements BeanPostProcessor {
    /**
     * 实例初始化之前调用
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化前执行的方法：Bean => " + beanName);
        return bean;
    }

    /**
     * 实例初始化之后调用
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化后执行的方法：Bean => " + beanName);
        return bean;
    }
}
