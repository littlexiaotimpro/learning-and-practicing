package com.pratice.redis.config;

import com.pratice.redis.bean.Example;
import com.pratice.redis.factory.DefaultYamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@PropertySource(value = {"classpath:/application.yml",
        "classpath:/application-default.yml",
        "classpath:/application-dev.yml"},
        factory = DefaultYamlPropertySourceFactory.class)
@EnableConfigurationProperties(RedisProperties.class)
public class AutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * Lettuce
     */
    @Bean
    @Profile("default-lettuce")
    public LettuceConnectionFactory defaultLettuceConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        return new LettuceConnectionFactory(config);
    }

    /**
     * Jedis
     */
    @Bean
    @Profile("default")
    public JedisConnectionFactory defaultJedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(redisProperties.getPassword());

        JedisClientConfiguration clientConfig = JedisClientConfiguration.defaultConfiguration();
        return new JedisConnectionFactory(config, clientConfig);
    }

    @Bean
    @Profile("dev")
    public JedisConnectionFactory devJedisConnectionFactory() {
        /*
            // RedisSentinelConfiguration 配置类中的定义方式
            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                   .master("mymaster")
                   .sentinel("127.0.0.1", 26379)
                   .sentinel("127.0.0.1", 26380);
         */
        // 自定义服务器及端口
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultJedisConnectionFactory());
        return redisTemplate;
    }


    @Bean
    public Example example() {
        return new Example();
    }

}
