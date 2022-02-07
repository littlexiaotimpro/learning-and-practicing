package com.example.mybatis.dao;

import com.example.mybatis.entity.LogBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * LogBeanDAO继承基类
 */
public interface LogBeanDAO extends MyBatisBaseDao<LogBean, Integer>{

    /**
     * 管理端获取所有日志
     */
    ArrayList<LogBean> findAll();

    String findOperatorByKey(@Param("logNo") String logNo);

    /**
     * 插入一条日志数据，验证事务属性
     */
    int insertOne(@Param("logBean") LogBean logBean);

    /**
     * 依据主键修改数据
     */
    int updateOne(@Param("logNo") String logNo, String operator);

}