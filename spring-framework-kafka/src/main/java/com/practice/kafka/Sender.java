package com.practice.kafka;

import com.practice.kafka.config.KafkaAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaAutoConfiguration.class);
        Sender sender = context.getBean(Sender.class);
        for (int i = 0; i < 5; i++) {
            sender.send("p_topic_message_" + i, i);
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
        this.template.send("p_topic", (key & 1) == 0 ? 0 : 4, key, toSend);
    }

}
