package com.practice.es.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * es存储的数据实体
 *
 * @since 2022/4/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String username;
    private String gender;
    private Integer age;

}
