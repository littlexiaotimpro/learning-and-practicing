package com.practice.redis.test;

import com.pratice.redis.bean.Example;
import com.pratice.redis.config.AutoConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class RedisConfigurationTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("spring.redis.host");
        System.out.println(property);

        Example example = applicationContext.getBean(Example.class);
        for (int i = 0; i < 15; i++) {
            example.add("admin", "value" + i);
        }
        List<String> admin = example.get("admin", 0, 20);
        System.out.println(admin);
        applicationContext.close();
    }

}
