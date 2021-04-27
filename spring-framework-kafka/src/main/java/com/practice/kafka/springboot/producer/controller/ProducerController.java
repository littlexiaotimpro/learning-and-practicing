package com.practice.kafka.springboot.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public void sendMessage() {
        kafkaTemplate.send("test", "kafka_test_message_" + (int) (Math.random() * 10));
    }

}
