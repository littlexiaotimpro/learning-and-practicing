package com.practice.test.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LogBean {
    private String logno;
    private String operator;
    private String operation;
    private String content;
    private Date createtime;
}
