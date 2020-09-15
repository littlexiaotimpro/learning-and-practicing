package com.practice.test.jdbc;

import com.practice.test.config.ComponentScanConfig;
import com.practice.test.entity.Log;
import com.practice.test.jdbc.service.JDBCTemplate;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JDBCDriverTest {

    @Test
    public void testSelect() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        JDBCTemplate bean = context.getBean(JDBCTemplate.class);
        String sql = "select * from tb_log where logNo = ?";
        Log log = bean.select(Log.class, sql,"1");
        System.out.println(log);
    }

    @Test
    public void testInsert() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        JDBCTemplate bean = context.getBean(JDBCTemplate.class);
        String sql = "insert into tb_log(logNo, operator,operation) values(?,?,?)";
        int insert = bean.insert(sql, "719", "test", "JDBC 测试");
        System.out.println(insert);
    }

}
