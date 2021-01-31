package com.pratice.redis.service.impl;

import com.pratice.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * redis 服务接口实现类
 */
@Service
public class RedisServiceImpl implements RedisService {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long timeout) {
        Boolean expire = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return Optional.ofNullable(expire).orElse(false);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
