package com.practice.test;

import com.practice.config.ComponentScanConfig;
import com.practice.service.DocumentHandlerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    @Test
    public void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config-more.xml");
        DocumentHandlerService documentInstance = context.getBean("documentInstance", DocumentHandlerService.class);
        documentInstance.uploadExcel();
    }

    @Test
    public void testXMLAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config-aop.xml");
        DocumentHandlerService documentInstance = context.getBean("documentInstance", DocumentHandlerService.class);
        documentInstance.uploadExcel();
    }

    @Test
    public void testAnnotationAop(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        DocumentHandlerService documentInstance = context.getBean("documentInstance", DocumentHandlerService.class);
        documentInstance.uploadExcel();
    }

}
