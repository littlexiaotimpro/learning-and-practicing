package com.practice.redis.test;

import com.pratice.redis.bean.Example;
import com.pratice.redis.bean.HashOperationsExample;
import com.pratice.redis.config.DemoRedisAutoConfiguration;
import com.pratice.redis.service.AuthCodeService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.text.MessageFormat;
import java.util.List;

public class RedisConfigurationTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedisAutoConfiguration.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("spring.redis.host");
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
    public void test_hash() throws IllegalAccessException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedisAutoConfiguration.class);
        HashOperationsExample example = applicationContext.getBean(HashOperationsExample.class);
        example.initValue();
        HashOperationsExample.User user = example.getValue("N_1");
        System.out.println(user);
        user = example.getValue("N_3");
        System.out.println(user);
        user = example.getValue("N_4");
        System.out.println(user);
        HashOperationsExample.Cache cache = example.getCache("cache");
        System.out.println(cache);
        applicationContext.close();
    }

    @Test
    public void testAuthCode() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoRedisAutoConfiguration.class);
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
