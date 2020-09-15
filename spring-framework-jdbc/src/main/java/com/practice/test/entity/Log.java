package com.practice.test.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author XiaoSi
 * @className Log
 * @description 日志
 * @date 2020/5/24
 */
@Data
public class Log {
    private String logNo;
    private String operator;
    private String operation;
    private String content;
    private String createtime;
}
