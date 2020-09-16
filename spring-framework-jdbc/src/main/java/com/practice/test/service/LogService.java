package com.practice.test.service;

import com.practice.test.dao.DataAccessor;
import com.practice.test.entity.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogService {

    private final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private DataAccessor dataAccessor;

    @Transactional
    public int deleteOne(String sql, Object... args) {
        int delete = dataAccessor.delete(sql, args);
        logger.info("执行的SQL：{}", sql);
        logger.info("参数列表：{}", args);
        logger.info("影响的结果数：{}", delete);
        return delete;
    }

    public Log selectOne(String sql, Object... args) throws Exception {
        return dataAccessor.select(Log.class, sql, args);
    }

}
