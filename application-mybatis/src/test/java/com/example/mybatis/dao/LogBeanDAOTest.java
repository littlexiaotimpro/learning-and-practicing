package com.example.mybatis.dao;

import com.example.mybatis.entity.LogBean;
import com.example.mybatis.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class LogBeanDAOTest {

    @Test
    public void findAll() throws Exception {
        List<LogBean> logBeans = MapperUtils.callMapper(LogBeanDAO.class, mapper -> {
            List<LogBean> list = new ArrayList<>();
            mapper.findAll(resultContext -> {
                DefaultResultContext<LogBean> context = (DefaultResultContext<LogBean>) resultContext;
                list.add(context.getResultObject());
                if (context.getResultCount() == 2) {
                    context.stop();
                }
            });
            return list;
        });
        System.out.println(logBeans);
    }

    @Test
    public void findOperatorByKey_01() {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            LogBeanDAO logBeanDAO = sqlSession.getMapper(LogBeanDAO.class);
            // 第一次查询
            String operator = logBeanDAO.findOperatorByKey("1");
            log.info("1 -> {}", operator);
            // 第二次查询
            operator = logBeanDAO.findOperatorByKey("1");
            log.info("2 -> {}", operator);
        }
    }

    @Test
    public void findOperatorByKey_02() {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            LogBeanDAO logBeanDAO = sqlSession.getMapper(LogBeanDAO.class);
            // 第一次查询
            String operator = logBeanDAO.findOperatorByKey("1");
            log.info("1 -> {}", operator);
            // 清除一级缓存
            sqlSession.clearCache();
            // 第二次查询
            operator = logBeanDAO.findOperatorByKey("1");
            log.info("2 -> {}", operator);
        }
    }

    @Test
    public void findOperatorByKey_03() {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            LogBeanDAO logBeanDAO = sqlSession.getMapper(LogBeanDAO.class);
            // 第一次查询
            String operator = logBeanDAO.findOperatorByKey("1");
            log.info("1 -> {}", operator);
            // 执行增删改操作清除缓存，因为重复查询可能会受修改结果影响
            LogBean logBean = LogBean.builder()
                    .operator("a")
                    .content("test a")
                    .build();
            logBeanDAO.insertOne(logBean);
            // 第二次查询
            operator = logBeanDAO.findOperatorByKey("1");
            log.info("2 -> {}", operator);
        }
    }

    @Test
    public void selectByPrimaryKey() {
        // 配置select 标签的 flushCache 属性值为true，每一次执行查询都先清除缓存
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            LogBeanDAO logBeanDAO = sqlSession.getMapper(LogBeanDAO.class);
            // 第一次查询
            LogBean logBean = logBeanDAO.selectByPrimaryKey(1);
            log.info("1 -> {}", logBean);
            // 第二次查询
            logBean = logBeanDAO.selectByPrimaryKey(1);
            log.info("2 -> {}", logBean);
        }
    }

    @Test
    public void insertOne() throws Exception {
        Integer aaa = MapperUtils.callMapper(LogBeanDAO.class, mapper -> {
            LogBean logBean = LogBean.builder()
                    .operator("bbb")
                    .content("test bbb")
                    .build();
            return mapper.insertOne(logBean);
        });
        assertEquals("", 1, aaa.intValue());
    }

    @Test
    public void updateOne() throws Exception {
        Integer aaa = MapperUtils.callMapper(LogBeanDAO.class, mapper -> {
            return mapper.updateOne("709", "aaa");
        });
        assertEquals("", 1, aaa.intValue());
    }
}