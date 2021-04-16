package com.practice.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

    @KafkaListener(id = "listen1", topics = "topic1")
    public void listen1(String in) {
        System.out.println("**********************");
        System.out.println(in);
        System.out.println("**********************");
    }

}
