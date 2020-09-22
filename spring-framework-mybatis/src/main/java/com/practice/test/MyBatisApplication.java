package com.practice.test;

import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@SpringBootApplication
//@RestController
public class MyBatisApplication {

    @Autowired
    LogBeanDAO logBeanDAO;

    @RequestMapping(value = "/")
    public List<LogBean> all() {
        return logBeanDAO.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
