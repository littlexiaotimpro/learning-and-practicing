package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.entity.Log;
import com.practice.test.repositories.DemoAnnotationRepository;
import com.practice.test.repositories.DemoQueryRepository;
import com.practice.test.repositories.SubMethodRepository;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class JPAAnnotationTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);

        DemoQueryRepository queryRepository = applicationContext.getBean(DemoQueryRepository.class);
        Log log = queryRepository.findById("747").orElse(new Log());
        System.out.println(log);
        applicationContext.close();
    }

    @Test
    public void testAnnotation(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        DemoAnnotationRepository queryRepository = applicationContext.getBean(DemoAnnotationRepository.class);
        Log log = queryRepository.findById("747");
        System.out.println(log);
        applicationContext.close();
    }

    @Test
    public void testMethod(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        Log log = queryRepository.findById("747").orElse(new Log());
        List<Log> logs = queryRepository.findByOperator("visitor");
        System.out.println(log);
        System.out.println(logs.size());
        applicationContext.close();
    }

    @Test
    public void testSubMethod(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        List<Log> pages = queryRepository.findByOperation("登录", PageRequest.of(1, 5));
        System.out.println(pages.size());
        List<Log> logs = queryRepository.selectByLimitOperation("新增");
        System.out.println(logs.size());
        applicationContext.close();
    }

    @Test
    public void testQuery(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        SubMethodRepository queryRepository = applicationContext.getBean(SubMethodRepository.class);
        List<Log> logs = queryRepository.queryLogs("visitor");
        System.out.println(logs.size());
        applicationContext.close();
    }
}
