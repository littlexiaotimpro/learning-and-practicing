package com.practice.test;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.entity.Log;
import com.practice.test.service.DataAccessor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataAccessorTest {

    @Test
    public void testSave() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        DataAccessor dataAccessor = applicationContext.getBean(DataAccessor.class);
        Log log = new Log();
        log.setLogNo("748")
                .setOperator("test-jpa")
                .setOperation("测试")
                .setContent("JPA 事务测试");
        dataAccessor.save(log);
        applicationContext.close();
    }

    @Test
    public void testDelete() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        DataAccessor dataAccessor = applicationContext.getBean(DataAccessor.class);
        Log log = new Log();
        log.setLogNo("748")
                .setOperator("test-jpa")
                .setOperation("测试")
                .setContent("JPA 事务测试");
        dataAccessor.delete(log);
        applicationContext.close();
    }
}
