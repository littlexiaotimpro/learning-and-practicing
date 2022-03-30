package com.practice.config;

import com.practice.deomfactory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 测试实例的集合注入
 *
 * @date 2022/3/30
 */
@Configuration
public class ConstructorConfiguration {

    @Bean
    public CompositeDemoFactory compositeDemoFactory(List<DemoFactory> demoFactories) {
        return new CompositeDemoFactory(demoFactories);
    }

    @Bean
    public DemoAFactory demoAFactory() {
        return new DemoAFactory();
    }

    @Bean
    public DemoBFactory demoBFactory() {
        return new DemoBFactory();
    }

    @Bean
    public DemoCFactory demoCFactory() {
        return new DemoCFactory();
    }
}
