package com.practice.test.service.impl;

import com.practice.test.entity.Log;
import com.practice.test.repositories.SubMethodRepository;
import com.practice.test.service.DataAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataAccessorImpl implements DataAccessor {

    @Autowired
    private SubMethodRepository subMethodRepository;

    @Override
    @Transactional
    public void save(Log log) {
        // JPA 默认的方法存在 REQUIRED 级别的事务
        // 在此逻辑方法上需要开启新事物，用于处理异常回滚
        Log res = subMethodRepository.save(log);
        System.out.println(res);
        int a = 10 / 0;
    }

    @Override
    @Transactional
    public void delete(Log log) {
        subMethodRepository.delete(log);
        int a = 10 / 0;
    }
}
