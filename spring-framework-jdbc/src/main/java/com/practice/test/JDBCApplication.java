package com.practice.test;

import com.practice.test.common.JDBCConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

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
