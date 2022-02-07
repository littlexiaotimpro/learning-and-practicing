package com.example.mybatis.dao;

import com.example.mybatis.entity.LogBean;
import com.example.mybatis.utils.MapperUtils;
import org.junit.Test;

import java.util.List;

public class LogBeanDAOTest {

    @Test
    public void findAll() throws Exception {
        List<LogBean> logBeans = MapperUtils.callMapper(LogBeanDAO.class, LogBeanDAO::findAll);
        System.out.println(logBeans);
    }

    @Test
    public void findOperatorByKey() {
    }

    @Test
    public void insertOne() {
    }

    @Test
    public void updateOne() {
    }
}