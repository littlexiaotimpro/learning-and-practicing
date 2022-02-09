package com.example.mybatis.handler;

import com.example.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义Mybatis类型处理：用户状态
 *
 * @see User.Status
 */
@Slf4j
public class UserStatusHandler extends BaseTypeHandler<User.Status> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, User.Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getTag());
        log.info("setNonNullParameter -> {}", parameter);
    }

    @Override
    public User.Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        log.info("getNullableResult -> {}", columnName);
        Object object = rs.getObject(columnName);
        Integer tag = object instanceof Integer ? (Integer) object : null;
        return User.Status.convert(tag);
    }

    @Override
    public User.Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        log.info("getNullableResult -> {}", columnIndex);
        Object object = rs.getObject(columnIndex);
        Integer tag = object instanceof Integer ? (Integer) object : null;
        return User.Status.convert(tag);
    }

    @Override
    public User.Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        log.info("getNullableResult -> {}", columnIndex);
        Object object = cs.getObject(columnIndex);
        Integer tag = object instanceof Integer ? (Integer) object : null;
        return User.Status.convert(tag);
    }
}
