package com.practice.test;


import com.practice.config.ConstructorConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorConfigurationTest {

    @Test
    public void test_00(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConstructorConfiguration.class);
        context.close();
    }

}
