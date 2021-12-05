package com.pratice.redis.config;

import com.pratice.redis.bean.Example;
import com.pratice.redis.bean.HashOperationsExample;
import com.pratice.redis.config.properties.AuthCodeProperties;
import com.pratice.redis.factory.DefaultYamlPropertySourceFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 不适用自动配置
 */
@Configuration
@ComponentScan(basePackages = {"com.pratice.redis.service"})
@PropertySource(value = {"classpath:/application.yml",
        "classpath:/application-jedis.yml",
        "classpath:/application-jedis-cluster.yml",
        "classpath:/application-lettuce.yml",
        "classpath:/application-lettuce-cluster.yml",
        "classpath:/application-sentinel.yml"},
        factory = DefaultYamlPropertySourceFactory.class)
@EnableConfigurationProperties({RedisProperties.class, AuthCodeProperties.class})
public class DemoRedisAutoConfiguration {

    private final RedisProperties redisProperties;

    public DemoRedisAutoConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /**
     * Lettuce
     */
    @SuppressWarnings("rawtypes")
    @Bean
    @Profile("lettuce")
    public LettuceConnectionFactory defaultLettuceConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(redisProperties.getDatabase());
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(redisProperties.getPassword());

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());

        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(redisProperties.getTimeout())
                .shutdownTimeout(redisProperties.getTimeout())
                .build();
        return new LettuceConnectionFactory(config, clientConfiguration);
    }

    /**
     * Lettuce-cluster
     */
    @SuppressWarnings("rawtypes")
    @Bean
    @Profile("lettuce-cluster")
    public LettuceConnectionFactory defaultLettuceClusterConnectionFactory() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        List<String> nodes = redisProperties.getCluster().getNodes();
        Set<RedisNode> redisNodes = nodes.stream().map(node -> {
            String[] rs = node.split(":");
            RedisNode.RedisNodeBuilder redisNodeBuilder = RedisNode.newRedisNode();
            return redisNodeBuilder.listeningAt(rs[0], Integer.parseInt(rs[1])).build();
        }).collect(Collectors.toSet());
        clusterConfiguration.setClusterNodes(redisNodes);

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());

        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(redisProperties.getTimeout())
                .shutdownTimeout(redisProperties.getTimeout())
                .build();
        return new LettuceConnectionFactory(clusterConfiguration, clientConfiguration);
    }

    /**
     * Jedis
     */
    @Bean
    @Profile("jedis")
    @SuppressWarnings("rawtypes")
    public JedisConnectionFactory defaultJedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(redisProperties.getDatabase());
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(redisProperties.getPassword());

        // 使用默认配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .readTimeout(redisProperties.getTimeout())
                .connectTimeout(redisProperties.getTimeout())
                .build();
        // build() 给 poolConfig 赋上了默认值
        GenericObjectPoolConfig poolConfig = clientConfig.getPoolConfig().get();
        poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        return new JedisConnectionFactory(config, clientConfig);
    }

    /**
     * Jedis-cluster
     */
    @Bean
    @Profile("jedis-cluster")
    @SuppressWarnings("rawtypes")
    public JedisConnectionFactory defaultJedisClusterConnectionFactory() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        List<String> nodes = redisProperties.getCluster().getNodes();
        Set<RedisNode> redisNodes = nodes.stream().map(node -> {
            String[] rs = node.split(":");
            RedisNode.RedisNodeBuilder redisNodeBuilder = RedisNode.newRedisNode();
            return redisNodeBuilder.listeningAt(rs[0], Integer.parseInt(rs[1])).build();
        }).collect(Collectors.toSet());
        clusterConfiguration.setClusterNodes(redisNodes);

        // 使用默认配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .readTimeout(redisProperties.getTimeout())
                .connectTimeout(redisProperties.getTimeout())
                .build();
        // build() 给 poolConfig 赋上了默认值
        GenericObjectPoolConfig poolConfig = clientConfig.getPoolConfig().get();
        poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        return new JedisConnectionFactory(clusterConfiguration, clientConfig);
    }

    @Bean
    @Profile("sentinel")
    public JedisConnectionFactory devJedisConnectionFactory() {
        // Redis 前哨配置
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new JedisConnectionFactory(sentinelConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
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
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


    @Bean
    public Example example() {
        return new Example();
    }

    @Bean
    public HashOperationsExample hashOperationsExample() {
        return new HashOperationsExample();
    }

}
