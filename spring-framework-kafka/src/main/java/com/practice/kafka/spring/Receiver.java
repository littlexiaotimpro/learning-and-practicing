package com.practice.kafka.spring;

import com.practice.kafka.spring.config.KafkaAutoConfiguration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Receiver {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaAutoConfiguration.class);
        DefaultKafkaConsumerFactory<Integer, String> consumerFactory = (DefaultKafkaConsumerFactory<Integer, String>) context.getBean(ConsumerFactory.class);
        consumerFactory.setBeanName("anotherConsumer");
        Consumer<Integer, String> consumer = consumerFactory.createConsumer();
        consumer.subscribe(Collections.singletonList("p_topic"));

        ExecutorService service = Executors.newFixedThreadPool(6);
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                service.submit(() -> {
                    for (ConsumerRecord<Integer, String> record : records) {
                        System.out.println("**********************");
                        System.out.println("* 当前线程: " + Thread.currentThread() + "\n"
                                + "* 偏移量: " + record.offset() + "\n"
                                + "* 主题: " + record.topic() + "\n"
                                + "* 分区: " + record.partition() + "\n"
                                + "* 获取的消息: " + record.value());
                        System.out.println("**********************");
                    }
                });
            }
        }
    }

}
