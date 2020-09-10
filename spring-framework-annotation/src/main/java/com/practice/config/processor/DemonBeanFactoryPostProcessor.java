package com.practice.config.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className DemonBeanFactoryPostProcessor
 * @description bean工厂的后置处理器，可以在这里自定义注入其它实例
 * @date 2020/9/10
 */
@Component
public class DemonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     *
     * @param beanFactory bean工厂，含有容器需要创建并注入的配置信息
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 1.获取bean定义并重新设置其中的相关属性，如作用域，懒加载等
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("demoBeanB");
        System.out.println(Arrays.toString(beanDefinition.attributeNames()));
        beanDefinition.setScope("prototype");
    }
}
