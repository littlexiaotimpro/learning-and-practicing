package com.practice.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

    @KafkaListener(id = "listen1", topics = "p_topic")
    public void listen1(ConsumerRecord<Integer, String> record) {
        System.out.println("**********************");
        System.out.println("* 当前线程: " + Thread.currentThread() + "\n"
                + "* 偏移量: " + record.offset() + "\n"
                + "* 主题: " + record.topic() + "\n"
                + "* 分区: " + record.partition() + "\n"
                + "* 获取的消息: " + record.value());
        System.out.println("**********************");
    }

    @KafkaListener(id = "listen2", topics = "p_topic")
    public void listen2(ConsumerRecord<Integer, String> record) {
        System.out.println("**********************");
        System.out.println("* 当前线程: " + Thread.currentThread() + "\n"
                + "* 偏移量: " + record.offset() + "\n"
                + "* 主题: " + record.topic() + "\n"
                + "* 分区: " + record.partition() + "\n"
                + "* 获取的消息: " + record.value());
        System.out.println("**********************");
    }

}
