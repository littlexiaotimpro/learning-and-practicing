package com.practice.test.config;

import com.practice.test.jdbc.config.JDBCConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.practice"})
@EnableConfigurationProperties(JDBCConfig.class)
public class ComponentScanConfig {
}
