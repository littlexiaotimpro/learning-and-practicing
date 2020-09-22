package com.practice.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 主要配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.practice"})
@MapperScan(basePackages = {"com.practice.test.dao"})
@PropertySource(value = {"classpath:/application.properties"})
@EnableConfigurationProperties({MybatisProperties.class})
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
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource,MybatisProperties mybatisProperties){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        return sqlSessionFactoryBean;
    }

}
