package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.entity.LogBean;
import com.practice.test.service.LogService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class MyBatisTest {

    private final String logNO = "720";

    @Test
    public void testFindAll(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证超时属性
        List<LogBean> all = logService.findAll();
        List<String> collect = all.stream().map(LogBean::getLogno).collect(Collectors.toList());
        System.out.println(collect);
        context.close();
    }

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
    public void testIsolationReadUncommitted(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        /*
         * 1.READ_UNCOMMITTED - 读未提交
         * 脏读：
         * 在cmd模式下：执行如下操作
         * start transaction;
         * update tb_log set operator = 'test_read_uncommitted' where logno =:logNo;
         * 在不提交的情况下，此时读取到的值正好为 test_read_uncommitted
         * 以这个值进行后续逻辑操作，若此时cmd的事务进行回滚，那么获取的值就是无效数据，而执行过的逻辑产生了错误结果
         *
         * 其他情况无法直接演示
         * 不可重复读：
         * 事务一读取初始值后，事务二进行数据修改，此时事务一再次读取数据，得到了修改后的数据，前后不一致
         *
         * 幻读：
         * 事务一读取初始范围值后，事务二进行数据新增，此时事务一再次读取相同范围的数据，其中出现了一些原来没有的数据
         */
        String operator = logService.selectOperator(logNO);
        System.out.println(operator);
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
