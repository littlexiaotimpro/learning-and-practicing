package com.practice.test.jdbc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author XiaoSi
 * @className JDBCConfig
 * @description TODO
 * @date 2020/5/23
 */
@Configuration
@Data
public class JDBCConfig {

    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;
    @Value(value = "${spring.datasource.password}")
    private String password;
}
