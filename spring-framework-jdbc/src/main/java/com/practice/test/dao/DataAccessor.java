package com.practice.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;

public class DataAccessor extends AbstractDataAccessor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(String sql, Object... args) {
        return 0;
    }

    @Override
    public int delete(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int update(String sql, Object... args) {
        return 0;
    }

    @Override
    public <T> T select(Class<T> tClass, String sql, Object... args) throws Exception {
        T t = tClass.newInstance();
        jdbcTemplate.query(sql, args, rs -> {
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++){
                Field field;
                try {
                    field = tClass.getDeclaredField(metaData.getColumnName(i));
                    field.setAccessible(true);
                    field.set(t, rs.getString(i));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return tClass.cast(t);
    }
}
