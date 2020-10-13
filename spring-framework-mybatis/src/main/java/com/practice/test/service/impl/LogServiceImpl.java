package com.practice.test.service.impl;

import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogBeanDAO logBeanDAO;

    @Override
    public List<LogBean> findAll() {
        return logBeanDAO.findAll();
    }

    @Override
    @Transactional(timeout = 3)
    public String checkTimeOut(String logNo) {
        try {
            // 验证事务超时属性，设置当前线程睡眠时间为 5s
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return logBeanDAO.findOperatorByKey(logNo);
    }

    @Override
    @Transactional(readOnly = true)
    public String checkReadOnly(String logNo) {
//        LogBean logBean = new LogBean();
//        logBean.setOperator("test");
//        logBean.setOperation("junit-test");
//        logBean.setContent("测试事务属性");
//        logBeanDAO.insertOne(logBean);
        return logBeanDAO.findOperatorByKey(logNo);
    }

    @Override
    public int insertOne(LogBean logBean) {
        return logBeanDAO.insertOne(logBean);
    }

    @Override
    @Transactional(rollbackFor = {FileNotFoundException.class})
//    @Transactional(rollbackForClassName = {"java.io.FileNotFoundException"})
    public int checkRollBackFor(String logNo, String operator) throws FileNotFoundException {
        int i = logBeanDAO.updateOne(logNo, operator);
        FileInputStream fileInputStream = new FileInputStream("D://test.test");
        return i;
    }

    @Override
    @Transactional(noRollbackFor = {ArithmeticException.class})
//    @Transactional(noRollbackForClassName = {"java.lang.ArithmeticException"})
    public int checkNoRollBackFor(String logNo, String operator) throws FileNotFoundException {
        int i = logBeanDAO.updateOne(logNo, operator);
        int a = 10/0;
        return i;
    }
}
