package com.practice.entity;

import com.practice.config.condition.BeanCreateCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Conditional(value = {BeanCreateCondition.class})
@Component
public class DemoBeanA {

    /**
     * 初始化
     * 对象创建及赋值之后，回调
     * 通过 @PostConstruct 的方式，注入初始化方法
     */
    @PostConstruct
    public void init(){
        System.out.println("初始化方法 === PostConstruct =====>" + getClass().getSimpleName());
    }

    /**
     * 销毁
     * 容器移除对象之前，回调
     * 通过 @PreDestroy 的方式，注入初始化方法
     */
    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法 === PreDestroy =====>" + getClass().getSimpleName());
    }

}
