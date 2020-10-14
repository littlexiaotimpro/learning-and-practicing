package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.service.LogService;
import com.practice.test.service.MulTransactionService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService logService = context.getBean(LogService.class);
        // 验证事务传播行为
        logService.checkPropagation();
        // 关闭容器
        context.close();
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        MulTransactionService mulTransactionService = context.getBean(MulTransactionService.class);
        // 验证事务传播行为
        mulTransactionService.checkPropagation();
        // 关闭容器
        context.close();
    }
}
