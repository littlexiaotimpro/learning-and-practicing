package com.pratice.redis.config;

import com.practice.common.factory.DefaultYamlPropertySourceFactory;
import com.pratice.redis.bean.Example;
import com.pratice.redis.config.properties.AuthCodeProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;

/**
 * redisson配置
 * - classpath:/application-redisson.yml
 * - classpath:/application-redisson-cluster.yml
 * 同时解析两个配置文件后解析的会把前一个解析的配置覆盖，无法达到切换环境切换的目的
 *
 * @date 2021/11/30
 */
@Configuration
@ComponentScan(basePackages = {"com.pratice.redis.service"})
@PropertySource(value = {"classpath:/application.yml",
        "classpath:/application-redisson-cluster.yml"},
        factory = DefaultYamlPropertySourceFactory.class)
@EnableConfigurationProperties({RedissonProperties.class, AuthCodeProperties.class})
public class DemoRedissonClusterConfiguration {

    @Bean
    public RedissonClient redissonCluster(RedissonProperties redissonProperties) throws IOException {
        return Redisson.create(Config.fromYAML(redissonProperties.getConfig()));
    }

    @Bean
    public RedisConnectionFactory defaultRedissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, String> stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


    @Bean
    public Example example() {
        return new Example();
    }

}
