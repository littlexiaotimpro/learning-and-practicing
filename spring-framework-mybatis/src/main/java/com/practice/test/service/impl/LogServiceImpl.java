package com.practice.test.service.impl;

import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String findOperator(String logNo) {
        return logBeanDAO.findOperatorByKey(logNo);
    }

    @Override
    public int insertOne(LogBean logBean) {
        return logBeanDAO.insertOne(logBean);
    }
}
