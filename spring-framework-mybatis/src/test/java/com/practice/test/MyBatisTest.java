package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MyBatisTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        String operator = logService.findOperator("1");
        System.out.println(operator);
        // 关闭容器
        context.close();
    }
}
