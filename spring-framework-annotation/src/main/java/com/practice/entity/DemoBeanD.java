package com.practice.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * 通过 PropertySource 加载配置文件
 */
@PropertySource(value = {"classpath:/props.properties"})
public class DemoBeanD {
    /**
     * Value 赋值
     * 基本数值：基本数据类型、包装类型、字符串
     * SpEL表达式：#{}
     * 读取配置文件中（加载进环境变量中）的值：${}
     */
    private String demoCode;
    @Value("0000")
    private String demoString;
    @Value("#{100-45}")
    private Integer demoInteger;
    @Value("${default.d-value}")
    public int demoInt;

    public void setDemoCode(String demoCode) {
        this.demoCode = demoCode;
    }

    public void setDemoString(String demoString) {
        this.demoString = demoString;
    }

    public void setDemoInteger(Integer demoInteger) {
        this.demoInteger = demoInteger;
    }
}
