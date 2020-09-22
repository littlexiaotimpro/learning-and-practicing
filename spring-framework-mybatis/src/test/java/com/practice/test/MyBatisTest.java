package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MyBatisTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogBeanDAO beanDAO = context.getBean(LogBeanDAO.class);
        List<LogBean> all = beanDAO.findAll();
        System.out.println(all);
        // 关闭容器
        context.close();
    }
}
