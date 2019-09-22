package com.practice.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/912:55
 */
@Accessors(chain = true)
@Data
public class User implements Serializable {
    private String code;
    private String userName;
    private int age;
    private char gender;
    private Date birthday;
}
