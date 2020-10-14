package com.practice.test.service.impl;

import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public String selectOperator(String logNo) {
        return logBeanDAO.findOperatorByKey(logNo);
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
    public int checkNoRollBackFor(String logNo, String operator) {
        int i = logBeanDAO.updateOne(logNo, operator);
        int a = 10/0;
        return i;
    }

    //----------------事务传播行为---------------------

    /**
     * 将多个事务操作直接在本类方法中调用
     */
    @Override
    @Transactional
    public void checkPropagation() {
        // 嵌套事务一
        transactionOne("720", "720-test-requires-new");
        // 嵌套事务二
        transactionTwo("721", "721-test-requires-new");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionOne(String logNo, String operator){
        logBeanDAO.updateOne(logNo,operator);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionTwo(String logNo, String operator){
        logBeanDAO.updateOne(logNo,operator);
        int i = 1/0;
    }
}
