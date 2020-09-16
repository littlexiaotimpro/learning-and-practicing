package com.practice.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.practice.test.dao.DataAccessor;
import com.practice.test.common.JDBCConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.practice"})
@EnableTransactionManagement
@PropertySource("classpath:/datasource.properties")
public class AutoConfiguration {

    @Bean("druidDataSource")
    public DataSource dataSource(JDBCConfig jdbcConfig){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getDriver());
        dataSource.setUrl(jdbcConfig.getUrl());
        dataSource.setUsername(jdbcConfig.getUsername());
        dataSource.setPassword(jdbcConfig.getPassword());
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
    public DataAccessor dataAccessor(){
        return new DataAccessor();
    }

}
