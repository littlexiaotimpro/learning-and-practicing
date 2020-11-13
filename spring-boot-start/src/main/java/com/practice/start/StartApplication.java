package com.practice.start;

import com.practice.start.common.DataBean;
import com.practice.start.config.TemplateConfig;
import com.practice.start.exception.SelfAnalyzerException;
import com.practice.start.primary.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties(TemplateConfig.class)
@ComponentScan
public class StartApplication {

    private final TemplateConfig templateConfig;

    @Autowired
    private final DataBean dataBean;

    public StartApplication(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
        // 触发自定义的异常分析
        throw new SelfAnalyzerException("Application start failure!!");
    }

    @RequestMapping(value = "/")
    public String home() {
        return templateConfig.getCode() + " : " + templateConfig.getName();
    }

    @RequestMapping(value = "/generate/id")
    public String key() {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String tName = Thread.currentThread().getName();
        long id = snowFlake.generateId(tName);
        return tName + " -> " + id;
    }

    @RequestMapping(value = "/subtract")
    public int getCount() {
        System.out.println("preCount = " + dataBean.getCount());
        dataBean.subtract();
        System.out.println("afterCount = " + dataBean.getCount());
        return dataBean.getCount();
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
