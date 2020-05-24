package com.practice.test.jdbc;

import com.practice.test.entity.Log;
import com.practice.test.jdbc.service.JDBCTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCDriverTest {

    @Autowired
    private JDBCTemplate jdbcTemplate;

    @Test
    public void testSelect() {
        String sql = "select * from tb_log where logNo = ?";
        Log log = jdbcTemplate.select(Log.class, sql,"1");
        System.out.println(log);
    }

}
