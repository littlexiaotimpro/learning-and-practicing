package com.pratice.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    // redis 基本属性
    private String host;
    private int database;
    private int port;
    private int timeout;
    private String password;

    // redis 验证码
    @Value("${spring.redis.key.prefix.authCode}")
    private String prefixAuthCode;
    @Value("${spring.redis.key.expire.authCode}")
    private Long expireAuthCode;

    // redis 客户端相关属性
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrefixAuthCode() {
        return prefixAuthCode;
    }

    public void setPrefixAuthCode(String prefixAuthCode) {
        this.prefixAuthCode = prefixAuthCode;
    }

    public Long getExpireAuthCode() {
        return expireAuthCode;
    }

    public void setExpireAuthCode(Long expireAuthCode) {
        this.expireAuthCode = expireAuthCode;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}
