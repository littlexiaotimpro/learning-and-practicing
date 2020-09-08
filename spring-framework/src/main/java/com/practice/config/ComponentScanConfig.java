package com.practice.config;

import com.practice.entity.BeanDemoAnnotation;
import com.practice.entity.BeanDemoConnect;
import org.springframework.context.annotation.*;

/**
 * 通过注解的方式配置组件扫描
 */
@Configuration
@ComponentScan(basePackages = "com.practice")
@EnableAspectJAutoProxy //注解的方式开启AOP自动代理
public class ComponentScanConfig {

    // @Bean 等同于XML中配置<bean id="" class=""/>，name/value为配置的实例名，如同id
    // @Scope 为bean的scope属性，同样有singleton和prototype属性值，表示单例及多例对象
    @Bean(name = "beanAnnotation")
    @Scope("singleton")
    public BeanDemoAnnotation getBeanDemoAnnotation(){
        return new BeanDemoAnnotation();
    }

    /**
     * 创建连接对象，用于测试属性注入
     */
    @Bean(name = "connect")
    public BeanDemoConnect getBeanDemoConnect(){
        return new BeanDemoConnect();
    }

}
