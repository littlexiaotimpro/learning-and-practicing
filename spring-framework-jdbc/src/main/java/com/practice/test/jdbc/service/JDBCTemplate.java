package com.practice.test.jdbc.service;

import com.practice.test.jdbc.JDBCDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author XiaoSi
 * @className JDBCTemplate
 * @description 模板方法具体实现
 * @date 2020/5/23
 */
@Component
public class JDBCTemplate extends AbstractJDBCTemplate {

    @Autowired
    private JDBCDriver driver;

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public <T> T select(Class<T> tClass, String sql, String... args) {
        Connection connection = driver.createConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, args[0]);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (int i=1;i <= resultSet.getMetaData().getColumnCount();i++){
                    System.out.println(resultSet.getString(i));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            driver.deployResultSet(resultSet);
            driver.deployPreparedStatement(preparedStatement);
            driver.deployConnection(connection);
        }
        return null;
    }
}
