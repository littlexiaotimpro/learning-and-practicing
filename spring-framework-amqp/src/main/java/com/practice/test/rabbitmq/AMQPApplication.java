package com.practice.test.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.practice.test.tut1.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.practice.test.xml.*")
})
public class AMQPApplication {

    public static void main(String[] args) {
        SpringApplication.run(AMQPApplication.class, args);
    }

}