package com.practice.test.jdbc;

import com.practice.test.config.ComponentScanConfig;
import com.practice.test.entity.Log;
import com.practice.test.jdbc.service.JDBCTemplate;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

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
    public void testInsert() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        JDBCTemplate bean = context.getBean(JDBCTemplate.class);
        String sql = "insert into tb_log(logNo, operator,operation) values(?,?,?)";
        int insert = bean.insert(sql, "719", "test", "719 - JDBC 测试");
        insert += bean.insert(sql, "718", "test", "718 - JDBC 测试");
        insert += bean.insert(sql, "717", "test", "717 - JDBC 测试");
        insert += bean.insert(sql, "716", "test", "716 - JDBC 测试");
        System.out.println(insert);
    }

    @Test
    public void testDelete() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        JDBCTemplate bean = context.getBean(JDBCTemplate.class);
        String sql = "delete from tb_log where logNo = ?";
        int delete = bean.delete(sql, "716");
        System.out.println(delete);
    }

}
