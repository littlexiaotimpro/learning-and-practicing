package com.practice.kafka.spring;

import com.practice.kafka.spring.config.KafkaSenderConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaSenderConfiguration.class);
        Sender sender = context.getBean(Sender.class);
        for (int i = 0; i < 5; i++) {
            String msg = "p_topic_message_" + i;
            System.out.println(msg);
            sender.send(msg, i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final KafkaTemplate<Integer, String> template;

    public Sender(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void send(String toSend, int key) {
        // 向指定分区发送消息
        this.template.send("p_topic", 0, key, toSend);
    }

}
