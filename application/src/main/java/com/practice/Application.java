package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XiaoSi
 * @className Application
 * @description 启动类
 * @date 2020/2/6
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("程序启动...");
        SpringApplication.run(Application.class,args);
    }
}
