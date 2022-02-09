package com.example.mybatis.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LogBean implements Serializable {
    private String logno;
    private String operator;
    private String operation;
    private String content;
    private Date createtime;
}
