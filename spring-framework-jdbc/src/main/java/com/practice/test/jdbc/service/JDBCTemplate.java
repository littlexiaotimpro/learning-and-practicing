package com.practice.test.jdbc.service;

import com.practice.test.jdbc.JDBCDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;

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
    @Autowired
    private DataSource dataSource;

    @Override
    public int insert(String sql, String ...args) throws SQLException {
        Connection connection = driver.createConnection();
        PreparedStatement preparedStatement = null;
        int res = 0;
        try {
            // 关闭自动提交
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }
            res = preparedStatement.executeUpdate();
            throw new SQLException("事务管理回滚");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            // 若存在异常，则事务回滚
            connection.rollback();
        } finally {
            // 手动提交事务
            connection.commit();
            driver.deployPreparedStatement(preparedStatement);
            driver.deployConnection(connection);
        }
        return res;
    }

    @Override
    @Transactional
    public int delete(String sql,String ...args) throws SQLException {
        int res = 0;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setString(i + 1, args[i]);
        }
        res = preparedStatement.executeUpdate();
//        int a = 10/0;
        return res;
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
        T t = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, args[0]);
            resultSet = preparedStatement.executeQuery();
            t = tClass.newInstance();
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++){
                    Field field = tClass.getDeclaredField(metaData.getColumnName(i));
                    field.setAccessible(true);
                    field.set(t, resultSet.getString(i));
                }
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        } finally {
            driver.deployResultSet(resultSet);
            driver.deployPreparedStatement(preparedStatement);
            driver.deployConnection(connection);
        }
        return t;
    }
}
