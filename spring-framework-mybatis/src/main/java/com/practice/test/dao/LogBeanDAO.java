package com.practice.test.dao;

import com.practice.test.entity.LogBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * LogBeanDAO继承基类
 */
@Repository
public interface LogBeanDAO extends MyBatisBaseDao<LogBean, Integer>{

    /**
     * 管理端获取所有日志
     */
    ArrayList<LogBean> findAll();

}