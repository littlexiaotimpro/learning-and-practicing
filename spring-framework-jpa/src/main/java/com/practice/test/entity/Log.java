package com.practice.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_log")
@Data
@Accessors(chain = true)
public class Log {
    @Id
    @Column(name = "logNo")
    private String logNo;
    @Column(name = "operator")
    private String operator;
    @Column(name = "operation")
    private String operation;
    @Column(name = "content")
    private String content;
    @Column(name = "createtime")
    private String createtime;
}
