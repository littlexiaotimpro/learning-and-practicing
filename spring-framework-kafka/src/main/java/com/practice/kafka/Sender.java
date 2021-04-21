package com.practice.kafka;

import com.practice.kafka.config.KafkaAutoConfiguration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.util.Collections;

public class Sender {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaAutoConfiguration.class);

        new Thread(() -> {
            DefaultKafkaConsumerFactory<Integer, String> consumerFactory = (DefaultKafkaConsumerFactory<Integer, String>) context.getBean(ConsumerFactory.class);
            consumerFactory.setBeanName("anotherConsumer");
            Consumer<Integer, String> consumer = consumerFactory.createConsumer();
            consumer.subscribe(Collections.singletonList("topic1"));

            while(true) {
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty()) {
                    System.out.println("**********************");
                    System.out.println("anotherConsumer-" + records.iterator().next().value());
                    System.out.println("**********************");
                }
            }
        }).start();

        Sender sender = context.getBean(Sender.class);
        sender.send("test1", 42);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sender.send("test2", 43);

    }

    private final KafkaTemplate<Integer, String> template;

    public Sender(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void send(String toSend, int key) {
        this.template.send("topic1", key, toSend);
    }

}
