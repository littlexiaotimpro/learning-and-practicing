package com.practice.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringValueResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 基于注解的 JPA 配置
 */
@Configuration
@ComponentScan(basePackages = {"com.practice.test"})
// EnableJpaRepositories的basePackages，默认扫描当前类所在的包下的组件
@EnableJpaRepositories(basePackages = {"com.practice.test.repositories"})
@EnableTransactionManagement
@PropertySource(value = {"classpath:/application.properties"})
public class AutoConfiguration implements EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    /**
     * 创建Druid数据源
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        String driver = resolver.resolveStringValue("${spring.datasource.driver}");
        dataSource.setDriverClassName(driver);
        String url = resolver.resolveStringValue("${spring.datasource.url}");
        dataSource.setUrl(url);
        String username = resolver.resolveStringValue("${spring.datasource.username}");
        dataSource.setUsername(username);
        String password = resolver.resolveStringValue("${spring.datasource.password}");
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Bean
    public DataSource hsqlDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.practice.test.entity");
        factory.setDataSource(dataSource());
        return factory;
    }


    /**
     * 声明式事务
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }
}
