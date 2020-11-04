package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.entity.Log;
import com.practice.test.repositories.DemoQueryRepository;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JPAAnnotationTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);

        DemoQueryRepository queryRepository = applicationContext.getBean(DemoQueryRepository.class);
        Log log = queryRepository.findById("747").orElse(new Log());
        System.out.println(log);
        applicationContext.close();
    }
}
