package com.practice.test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_log")
@Data
public class Log {
    @Id
    private String logNo;
    private String operator;
    private String operation;
    private String content;
    private String createtime;
}
