package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.dao.LogBeanDAO;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.util.List;

public class MyBatisTest {

    private final String logNO = "720";

    @Test
    public void testTimeOut() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证超时属性
        String operator = logService.checkTimeOut(logNO);
        System.out.println(operator);
        context.close();
    }

    @Test
    public void testReadOnly() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证只读属性
        String operator = logService.checkReadOnly(logNO);
        System.out.println(operator);
        // 关闭容器
        context.close();
    }

    @Test
    public void testRollBackFor() throws FileNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证事务异常回滚
        int i = logService.checkRollBackFor(logNO,"test");
        System.out.println("操作结果：" + i);
        // 关闭容器
        context.close();
    }

    @Test
    public void testNoRollBackFor() throws FileNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证事务异常回滚
        int i = logService.checkNoRollBackFor(logNO,"testException");
        System.out.println("操作结果：" + i);
        // 关闭容器
        context.close();
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证事务异常回滚
        LogBean logBean = new LogBean();
        logBean.setOperator("test");
        logBean.setOperation("junit-test");
        logBean.setContent("测试事务属性");
        int i = logService.insertOne(logBean);
        System.out.println("操作结果：" + i);
        // 关闭容器
        context.close();
    }
}
