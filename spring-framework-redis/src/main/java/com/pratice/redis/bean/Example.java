package com.pratice.redis.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public class Example {

    // inject the actual template
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // inject the template as ListOperations
    // list 集合操作
    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOps;

    // 键值对操作
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;

    public Example() {
    }

    public void addValue(String k, Object v) {
        // 为 key 设定值
        valueOps.set(k, v.toString());
    }

    public Object getValue(String k) {
        return valueOps.get(k);
    }

    public void add(String userId, Object value) {
        // 向左插入数据
        listOps.leftPush(userId, value);
        // 向右插入数据
        listOps.rightPush(userId, value);
    }

    public List<Object> get(String userId, long l, long r) {
        Boolean res = Optional.ofNullable(redisTemplate.hasKey(userId)).orElse(false);
        if (res) {
            long size = Optional.ofNullable(listOps.size(userId)).orElse(0L);
            return listOps.range(userId, l, Math.min(r, size));
        } else {
            return null;
        }
    }
}
