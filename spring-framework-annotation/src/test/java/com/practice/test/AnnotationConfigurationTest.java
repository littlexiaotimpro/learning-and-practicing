package com.practice.test;

import com.practice.config.ComponentScanConfig;
import com.practice.config.aware.DemoBeanAware;
import com.practice.entity.DemoBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigurationTest {

    @Test
    public void test() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        printBean(context);
        DemoBeanAware beanAware = context.getBean(DemoBeanAware.class);
        DemoBean bean = context.getBean("demoBean", DemoBean.class);
        String property = context.getEnvironment().getProperty("default.c-value");
        String profile = context.getEnvironment().getProperty("spring.profiles.active");
        System.out.println(property + " / " + profile);
        // 不符合Profile配置的环境无法加载
        // DemoBeanE beanE = context.getBean(DemoBeanE.class);
        // 手动关闭容器，singleton的实例会执行其对象销毁方法
        context.close();
    }

    private void printBean(ApplicationContext context) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
