package com.practice.test.rabbitmq;

import com.practice.test.xml.config.RabbitConfiguration;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author XiaoSi
 * @className XMLConfigurationTest
 * @description 注解配置测试
 * @date 2020/11/13
 */
public class AnnotationConfigurationTest {

    @Test
    public void test() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");
    }

}
