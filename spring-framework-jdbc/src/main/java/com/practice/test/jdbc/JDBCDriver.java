package com.practice.test.jdbc;

import com.practice.test.common.JDBCConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @author XiaoSi
 * @className JDBCDriver
 * @description 获取数据连接
 * @date 2020/5/23
 */
@Component
public class JDBCDriver {

    @Autowired
    private JDBCConfig config;

    /**
     * 获取连接
     */
    public Connection createConnection(){
        Connection conn = null;
        try {
            Class.forName(config.getDriver());
            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void deployConnection(Connection conn){
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deployPreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deployResultSet(ResultSet resultSet){
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
