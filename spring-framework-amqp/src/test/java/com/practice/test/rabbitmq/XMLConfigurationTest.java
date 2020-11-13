package com.practice.test.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author XiaoSi
 * @className XMLConfigurationTest
 * @description XML配置测试
 * @date 2020/11/13
 */
public class XMLConfigurationTest {

    @Test
    public void test() {
        ApplicationContext context =
                new GenericXmlApplicationContext("classpath:/rabbit-context.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");
    }

}
