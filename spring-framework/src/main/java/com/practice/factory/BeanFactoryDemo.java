package com.practice.factory;

import com.practice.entity.BeanDemo;
import org.springframework.beans.factory.FactoryBean;

/**
 * XML 配置的工厂Bean
 * 不指定泛型时，默认返回一个 Object 对象
 * 指定具体的泛型，则返回具体类型的对象
 */
public class BeanFactoryDemo implements FactoryBean<BeanDemo> {

    @Override
    public BeanDemo getObject() throws Exception {
        return new BeanDemo();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanDemo.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
