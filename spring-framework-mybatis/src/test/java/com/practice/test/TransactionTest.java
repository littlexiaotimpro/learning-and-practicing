package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.service.LogService;
import com.practice.test.service.MulTransactionService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {

    @Test
    public void test00() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证事务传播行为
        logService.checkPropagation();
        // 关闭容器
        context.close();
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation01();
        // 关闭容器
        context.close();
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation02();
        // 关闭容器
        context.close();
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation03();
        // 关闭容器
        context.close();
    }

    @Test
    public void test04() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation04();
        // 关闭容器
        context.close();
    }

    @Test
    public void test05() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation05();
        // 关闭容器
        context.close();
    }

    @Test
    public void test06() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation06();
        // 关闭容器
        context.close();
    }

    @Test
    public void test07() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation07();
        // 关闭容器
        context.close();
    }

    @Test
    public void test08() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagationConcurrent();
        // 关闭容器
        context.close();
    }
}
