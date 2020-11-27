package com.pratice.redis.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public class Example {

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // inject the template as ListOperations
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public void add(String userId, String value) {
        // 向左插入数据
        listOps.leftPush(userId, value);
        // 向右插入数据
        listOps.rightPush(userId, value);
    }

    public List<String> get(String userId, long l, long r) {
        Boolean res = Optional.ofNullable(redisTemplate.hasKey(userId)).orElse(false);
        if (res) {
            long size = Optional.ofNullable(listOps.size(userId)).orElse(0L);
            return listOps.range(userId, l, Math.min(r, size));
        } else {
            return null;
        }
    }
}
