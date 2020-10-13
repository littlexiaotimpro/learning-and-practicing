package com.practice.test.service;

import com.practice.test.entity.LogBean;

import java.util.List;

public interface LogService {

    List<LogBean> findAll();

    String findOperator(String logNo);

    int insertOne(LogBean logBean);

}
