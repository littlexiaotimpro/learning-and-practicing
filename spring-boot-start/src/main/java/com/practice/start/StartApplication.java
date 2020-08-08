package com.practice.start;

import com.practice.start.config.TemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties(TemplateConfig.class)
public class StartApplication {

    private TemplateConfig templateConfig;

    public StartApplication(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
    }

    @RequestMapping(value = "/")
    public String home() {
        return templateConfig.getCode() + " : " + templateConfig.getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
