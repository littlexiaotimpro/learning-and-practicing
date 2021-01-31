package com.pratice.redis.config;

import com.pratice.redis.bean.Example;
import com.pratice.redis.factory.DefaultYamlPropertySourceFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = {"com.pratice.redis.service"})
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
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        return new LettuceConnectionFactory(config);
    }

    /**
     * Jedis
     */
    @Bean
    @Profile("default")
    @SuppressWarnings("rawtypes")
    public JedisConnectionFactory defaultJedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(redisProperties.getDatabase());
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(redisProperties.getPassword());

        // 使用默认配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .readTimeout(Duration.ofMillis(redisProperties.getTimeout()))
                .connectTimeout(Duration.ofMillis(redisProperties.getTimeout()))
                .build();
        // build() 给 poolConfig 赋上了默认值
        GenericObjectPoolConfig poolConfig = clientConfig.getPoolConfig().get();
        poolConfig.setMinIdle(redisProperties.getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        return new JedisConnectionFactory(config, clientConfig);
    }

    @Bean
    @Profile("dev")
    public JedisConnectionFactory devJedisConnectionFactory() {
        // Redis 前哨配置
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new JedisConnectionFactory(sentinelConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, String> stringRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultJedisConnectionFactory());
        return redisTemplate;
    }


    @Bean
    public Example example() {
        return new Example();
    }

}
