package com.practice.test;

import com.practice.test.entity.Log;
import com.practice.test.repositories.DemoAnnotationRepository;
import com.practice.test.repositories.DemoQueryRepository;
import com.practice.test.repositories.SubMethodRepository;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class JPAXMLTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource-jpa.xml");
        DemoQueryRepository queryRepository = applicationContext.getBean(DemoQueryRepository.class);
        Log log = queryRepository.findById("747").orElse(new Log());
        System.out.println(log);
        applicationContext.close();
    }

    @Test
    public void testAnnotation(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource-jpa.xml");
        DemoAnnotationRepository queryRepository = applicationContext.getBean(DemoAnnotationRepository.class);
        Log log = queryRepository.findById("747");
        System.out.println(log);
        applicationContext.close();
    }

    @Test
    public void testMethod(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource-jpa.xml");
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        Log log = queryRepository.findById("747").orElse(new Log());
        List<Log> logs = queryRepository.findByOperator("visitor");
        System.out.println(log);
        System.out.println(logs.size());
        applicationContext.close();
    }

    @Test
    public void testSubMethod(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource-jpa.xml");
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        List<Log> pages = queryRepository.findByOperation("登录", PageRequest.of(1, 5));
        System.out.println(pages.size());
        List<Log> logs = queryRepository.selectByLimitOperation("新增");
        System.out.println(logs.size());
        applicationContext.close();
    }

    @Test
    public void testQuery(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource-jpa.xml");
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        List<Log> logs = queryRepository.queryLogs("visitor");
        System.out.println(logs.size());
        applicationContext.close();
    }
}
