package com.practice.config.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 通过将此自定义的后置处理器添加进容器组件扫描的内容中，会自动调用
 * @author XiaoSi
 * @className DemoBeanPostProcessor
 * @description 自定义Bean后置处理器，在对象初始化前后会调用响相应的方法
 * @date 2020/9/10
 */
@Component
public class DemoBeanPostProcessor implements BeanPostProcessor {

    /**
     * 对象初始化前调用的方法
     * 一般需要单独判断具体需要处理哪些实例
     * @param bean     对象实例
     * @param beanName 对象实例的定义名称
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器的初始化前置方法========>" + beanName);
        return bean;
    }

    /**
     * 对象初始化后调用的方法
     * @param bean     对象实例
     * @param beanName 对象实例的定义名称
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器的初始化后置方法========>" + beanName);
        return bean;
    }
}
