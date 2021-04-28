package com.practice.test.service;

import com.practice.test.entity.LogBean;

import java.io.FileNotFoundException;
import java.util.List;

public interface LogService {

    List<LogBean> findAll();

    String checkTimeOut(String logNo);

    String checkReadOnly(String logNo);

    int checkRollBackFor(String logNo, String operator) throws FileNotFoundException;

    int checkNoRollBackFor(String logNo, String operator) throws FileNotFoundException;

    int insertOne(LogBean logBean);

    String selectOperator(String logNo);

    void checkPropagation();

    void transactionRequired(String logNo, String operator);

    void transactionRequiresNew(String logNo, String operator);

    void transactionRequiresNewThrow(String logNo, String operator);

    void transactionSupports(String logNo, String operator);

    void transactionMandatory(String logNo, String operator);

    void transactionNotSupported(String logNo, String operator);

    void transactionNever(String logNo, String operator);

}
