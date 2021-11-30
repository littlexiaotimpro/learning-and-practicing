package com.pratice.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
public class AuthCodeProperties {

    // redis 验证码
    private AuthCode authCode;

    public AuthCode getAuthCode() {
        return authCode;
    }

    public void setAuthCode(AuthCode authCode) {
        this.authCode = authCode;
    }

    public static class AuthCode {
        private String prefix;
        private Long expire;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public Long getExpire() {
            return expire;
        }

        public void setExpire(Long expire) {
            this.expire = expire;
        }
    }
}
