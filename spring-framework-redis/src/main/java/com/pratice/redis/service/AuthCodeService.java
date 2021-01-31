package com.pratice.redis.service;

/**
 * 验证码测试接口
 *
 * create at 2021-01-31
 */
public interface AuthCodeService {

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    String verifyAuthCode(String telephone, String authCode);

}
