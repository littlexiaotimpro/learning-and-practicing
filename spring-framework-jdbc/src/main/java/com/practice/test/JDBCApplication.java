package com.practice.test;

import com.practice.test.jdbc.config.JDBCConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//@RestController
public class JDBCApplication {

    @Autowired
    JDBCConfig jdbcConfig;

    @RequestMapping(value = "/")
    public String jdbcConfig() {
        return "{url=" + jdbcConfig.getUrl() + "}";
    }

    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class, args);
    }
}
