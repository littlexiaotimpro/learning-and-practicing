package com.pratice.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class AutoConfiguration {

    @Bean
    @Profile("default")
    public RedisConnectionFactory defaultRedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    @Profile("dev")
    public RedisConnectionFactory devRedisConnectionFactory(){
        // 自定义服务器及端口
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(config);
    }

}
