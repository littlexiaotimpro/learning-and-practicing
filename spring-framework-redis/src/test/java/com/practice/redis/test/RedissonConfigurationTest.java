package com.practice.redis.test;

import com.pratice.redis.bean.Example;
import com.pratice.redis.config.DemoRedissonClusterConfiguration;
import com.pratice.redis.config.DemoRedissonSingleConfiguration;
import com.pratice.redis.service.AuthCodeService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.text.MessageFormat;
import java.util.List;

public class RedissonConfigurationTest {

    @Test
    public void test_single() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedissonSingleConfiguration.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("spring.redis");
        System.out.println(property);

        Example example = applicationContext.getBean(Example.class);
        for (int i = 0; i < 15; i++) {
            example.add("admin", "value" + i);
        }
        List<Object> admin = example.get("admin", 0, 20);
        System.out.println(admin);

        example.addValue("prefix", "add");

        Object prefix = example.getValue("prefix");
        System.out.println(prefix.toString());

        applicationContext.close();
    }

    @Test
    public void test_cluster() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedissonClusterConfiguration.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("spring.redis");
        System.out.println(property);

        Example example = applicationContext.getBean(Example.class);
        for (int i = 0; i < 15; i++) {
            example.add("admin", "value" + i);
        }
        List<Object> admin = example.get("admin", 0, 20);
        System.out.println(admin);

        example.addValue("prefix", "add");

        Object prefix = example.getValue("prefix");
        System.out.println(prefix.toString());

        applicationContext.close();
    }

    @Test
    public void testAuthCode() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedissonSingleConfiguration.class);
        AuthCodeService authCodeService = applicationContext.getBean(AuthCodeService.class);
        String phone = "17240124094";
        // 生成验证码
        String authCode = authCodeService.generateAuthCode(phone);
        System.out.println(MessageFormat.format("获取验证码成功，验证码: {0}", authCode));
        // 校验验证码
        String res = authCodeService.verifyAuthCode(phone, null);
        System.out.println(res);
        res = authCodeService.verifyAuthCode(phone, authCode + "w");
        System.out.println(res);
        res = authCodeService.verifyAuthCode(phone, authCode);
        System.out.println(res);
        applicationContext.close();
    }

}
