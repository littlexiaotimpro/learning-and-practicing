package com.practice.start.config;

import com.practice.start.common.DataBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    public DataBean dataBean(){
        return new DataBean();
    }
}
