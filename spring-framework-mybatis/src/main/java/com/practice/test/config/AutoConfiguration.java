package com.practice.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
// 启用事务管理
@EnableTransactionManagement
// 启用自动配置，会加载很多无用的配置
//@EnableAutoConfiguration
public class AutoConfiguration {

    /**
     * 测试类配置数据源
     * @param jdbcConfig
     * @return
     */
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
     * 1.自动配置(不需要配置数据源的Bean注入和一下sqlSession注入)：
     * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(DataSource)}
     * 在上述的 MyBatis 自动配置类中，如下：
     * <code>
     *   // @Bean 注入一个 Bean
     *   // @ConditionalOnMissingBean 使用此注解修饰后，若当前容器中存在SqlSessionFactory对应的实例，则此方法不执行
     *   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
     *       // ...
     *   }
     * </code>
     * 2.测试类配置 sqlSession
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource,MybatisProperties mybatisProperties){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 默认的解析路径是和 MapperScan 扫描路径对应的DAO的路径，如：
        // com.practice.test.dao.LogBeanDAO.java，对应的xml文件路径为：classpath:com/practice/test/dao/LogBeanDAO.xml
        // 如果需要启动资源配置文件中配置的 mybatis的本地资源路径，需要如下解析方式
        //sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        //sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        return sqlSessionFactoryBean;
    }

    /**
     * 创建事务管理器对象
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource druidDataSource){
        return new DataSourceTransactionManager(druidDataSource);
    }

}
