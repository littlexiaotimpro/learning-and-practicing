package com.practice.start.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.practice.start.common.DataBean;
import com.practice.start.interceptor.SelfInterceptor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({DataSourceProperties.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AutoConfiguration implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource(DataSourceProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource druidDataSource){
        return new JdbcTemplate(druidDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource druidDataSource){
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean
    public DataBean dataBean(){
        return new DataBean();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SelfInterceptor())
        .addPathPatterns("/**");
    }
}
