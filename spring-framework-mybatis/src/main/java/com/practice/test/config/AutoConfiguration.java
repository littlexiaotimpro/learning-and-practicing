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
/*
 * 测试类中需要注入相关属性
 */
@PropertySource(value = {"classpath:/application.properties"})
@EnableConfigurationProperties({MybatisProperties.class})
public class AutoConfiguration {

    @Bean
    public DataSource dataSource(JDBCConfig jdbcConfig){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getDriver());
        dataSource.setUrl(jdbcConfig.getUrl());
        dataSource.setUsername(jdbcConfig.getUsername());
        dataSource.setPassword(jdbcConfig.getPassword());
        return dataSource;
    }

    /**
     * 使用自动配置（需要配置数据源）：
     * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(DataSource)}
     * 在上述的 MyBatis 自动配置类中，如下：
     * <code>
     *   // @Bean 注入一个 Bean
     *   // @ConditionalOnMissingBean 使用此注解修饰后，若当前容器中存在SqlSessionFactory对应的实例，则此方法不执行
     *   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
     *       // ...
     *   }
     * </code>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource,MybatisProperties mybatisProperties){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 默认的解析路径是和 MapperScan 扫描路径对应的DAO的路径，如：
        // com.practice.test.dao.LogBeanDAO.java，对应的xml文件路径为：classpath:com/practice/test/dao/LogBeanDAO.xml
        // 如果需要启动资源配置文件中配置的 mybatis的本地资源路径，需要如下解析方式
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        return sqlSessionFactoryBean;
    }

}
