package com.practice.test.config;

import com.practice.test.jdbc.custom.CustomDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.practice"})
@PropertySource("classpath:/datasource.properties")
@EnableTransactionManagement
public class ComponentScanConfig {

    @Bean
    public DataSource dataSource(){
        return new CustomDataSource();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
