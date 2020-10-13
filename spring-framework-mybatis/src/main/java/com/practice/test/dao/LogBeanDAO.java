package com.practice.test.dao;

import com.practice.test.entity.LogBean;
import org.apache.ibatis.annotations.Param;
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

    String findOperatorByKey(@Param("logNo") String logNo);

    /**
     * 插入一条日志数据，验证事务属性
     */
    int insertOne(LogBean logBean);

}