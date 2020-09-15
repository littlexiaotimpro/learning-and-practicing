package com.practice.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.practice"})
@PropertySource("classpath:/datasource.properties")
public class ComponentScanConfig {
}
