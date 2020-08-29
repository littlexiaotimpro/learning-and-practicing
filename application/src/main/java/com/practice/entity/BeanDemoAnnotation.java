package com.practice.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class BeanDemoAnnotation {
    // 注入属性值，无需提供setter方法
    @Value("1111")
    private String demoCode;

    // 注入对象属性
    // @Autowired 单独使用此注解，默认以byType的模式进行对象注入，即按类型匹配注入对象
    @Autowired
    // @Qualifier 和@Autowired同时使用，用于表示使用的是byName的格式注入，即按属性名称匹配的方式注入对象
    @Qualifier("connect")
    // @Resource 指定name属性，可以按名称为属性注入对象
    // 需要注意，此注解并不是Spring的，而是jdk扩展包中的注解类，建议是通过@Autowired注解的方式注入属性
    // @Resource(name = "connect")
    private BeanDemoConnect connect;

    // 提供带参构造器，最好提供默认的无参构造器
    public BeanDemoAnnotation() {
    }

    public BeanDemoAnnotation(BeanDemoConnect connect) {
        this.connect = connect;
    }

    public void setConnect(BeanDemoConnect connect) {
        this.connect = connect;
    }

    /**
     * 级联赋值获取对象
     */
    public BeanDemoConnect getConnect() {
        return connect;
    }

    @Override
    public String toString() {
        return getClass() + " {demoCode=" + demoCode + ", connect=" + connect + "}";
    }
}
