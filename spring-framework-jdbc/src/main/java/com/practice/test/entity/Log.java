package com.practice.test.entity;

import com.practice.test.annotation.Column;
import com.practice.test.annotation.Id;
import lombok.Data;

/**
 * @author XiaoSi
 * @className Log
 * @description 日志
 * @date 2020/5/24
 */
@Data
public class Log {

    @Id
    @Column(value = "logNo")
    private String logNo;

    @Column(value = "operator")
    private String logOperator;

    @Column(value = "operation")
    private String logOperation;

    @Column(value = "content")
    private String logContent;

    @Column(value = "createtime")
    private String logCreateTime;
}
