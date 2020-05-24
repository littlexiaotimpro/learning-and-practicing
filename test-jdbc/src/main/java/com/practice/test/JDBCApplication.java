package com.practice.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
public class JDBCApplication {

    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class,args);
    }
}
