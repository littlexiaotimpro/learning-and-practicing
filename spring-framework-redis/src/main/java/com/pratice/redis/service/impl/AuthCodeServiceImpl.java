package com.pratice.redis.service.impl;

import com.pratice.redis.config.RedisProperties;
import com.pratice.redis.service.AuthCodeService;
import com.pratice.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class AuthCodeServiceImpl implements AuthCodeService {


    private RedisService redisService;
    private RedisProperties redisProperties;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setRedisProperties(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        String key = redisProperties.getPrefixAuthCode() + telephone;
        redisService.set(key, sb.toString());
        redisService.expire(key, redisProperties.getExpireAuthCode());
        return sb.toString();
    }

    @Override
    public String verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return "请输入验证码";
        }
        String realAuthCode = redisService.get(redisProperties.getPrefixAuthCode() + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return "验证码校验成功";
        } else {
            return "验证码不正确";
        }
    }
}
