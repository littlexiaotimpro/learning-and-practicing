package com.pratice.application.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
@Slf4j
public class DemoRedisController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 数据双写不一致
     * 并发30次请求
     */
    @PostMapping(value = "/double/write")
    public void doubleWrite() {
        int i = (int) (Math.random() * 100);
        log.info("数据库修改数据【{}】", i);
        // 先更新数据库
        String sql = "update tb_store set stack = ? where id = ?";
        jdbcTemplate.update(sql, i, 1);
        // 再更新缓存
        stringRedisTemplate.opsForValue().set("stack", String.valueOf(i));
    }

}
