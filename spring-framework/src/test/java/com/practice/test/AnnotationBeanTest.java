package com.practice.test;

import com.practice.config.ComponentScanConfig;
import com.practice.entity.BeanDemoAnnotation;
import com.practice.service.DocumentHandlerService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBeanTest {

    @Test
    public void testAnnotationComponentScan() {
        // 获取添加了@Configuration注解的配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        // @Service注解修饰的实现类能够通过类型获取实例
        // com.practice.service.impl.DocumentHandlerServiceImpl@682c1e93
        DocumentHandlerService documentInstance = context.getBean(DocumentHandlerService.class);
        System.out.println(documentInstance);

        /*
         * 如果没有@Component注解相关的注解修饰，无法直接获取对象
         * No qualifying bean of type 'com.practice.entity.BeanDemoAnnotation' available
         * 在配置类中使用@Bean注释一个具有具体类型返回值的方法
         * 如同和XML中的bean定义类似，可以自己自定实例名，对value或name赋值如下：
         *
         * @Bean(name = "beanAnnotation")
         * public BeanDemoAnnotation getBeanDemoAnnotation(){
         *    return new BeanDemoAnnotation();
         * }
         *
         * class com.practice.entity.BeanDemoAnnotation {connect=null}
         */
        BeanDemoAnnotation beanDemo = context.getBean(BeanDemoAnnotation.class);
        System.out.println(beanDemo);
    }
}
