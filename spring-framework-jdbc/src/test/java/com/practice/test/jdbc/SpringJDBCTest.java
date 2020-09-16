package com.practice.test.jdbc;

import com.practice.test.config.AutoConfiguration;
import com.practice.test.entity.Log;
import com.practice.test.service.LogService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJDBCTest {

    @Test
    public void testSelect() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService bean = context.getBean(LogService.class);
        String sql = "select * from tb_log where logNo = ?";
        Log log = bean.selectOne(sql, "719");
        System.out.println(log);
    }

    @Test
    public void testDelete() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        LogService bean = context.getBean(LogService.class);
        String sql = "delete from tb_log where logNo = ?";
        int res = bean.deleteOne(sql, "717");
        System.out.println(res);
    }

}
