package com.practice.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

    @KafkaListener(id = "listen1", topics = "p_topic")
    public void listen1(String in) {
        System.out.println("**********************");
        System.out.println("listen1-" + in);
        System.out.println("**********************");
    }

    @KafkaListener(id = "listen2", topics = "p_topic")
    public void listen2(String in) {
        System.out.println("**********************");
        System.out.println("listen2-" + in);
        System.out.println("**********************");
    }

}
