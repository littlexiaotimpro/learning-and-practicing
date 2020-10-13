package com.practice.test.service;

import com.practice.test.entity.LogBean;

import java.io.FileNotFoundException;
import java.util.List;

public interface LogService {

    List<LogBean> findAll();

    String checkTimeOut(String logNo);

    String checkReadOnly(String logNo);

    int insertOne(LogBean logBean);

    int checkRollBackFor(String logNo,String operator) throws FileNotFoundException;

    int checkNoRollBackFor(String logNo,String operator) throws FileNotFoundException;

}
