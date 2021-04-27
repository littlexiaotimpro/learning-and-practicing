package com.practice.kafka.springboot.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("test")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId", topics = "test")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("**********************");
        System.out.println("* 当前线程: " + Thread.currentThread() + "\n"
                + "* 偏移量: " + record.offset() + "\n"
                + "* 主题: " + record.topic() + "\n"
                + "* 分区: " + record.partition() + "\n"
                + "* 获取的消息: " + record.value());
        System.out.println("**********************");
    }

}
