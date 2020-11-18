package com.practice.test.dao;

import com.practice.test.annotation.Column;
import com.practice.test.annotation.Id;
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
    public <T> int delete(T t) {
        Class<?> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Id[] ids = field.getAnnotationsByType(Id.class);
        }
        return 0;
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
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Field[] declaredFields = tClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    try {
                        field.setAccessible(true);
                        Column[] mapping = field.getAnnotationsByType(Column.class);
                        if (mapping.length > 0) {
                            Column column = mapping[0];
                            String value = column.value();
                            if (value.equals(metaData.getColumnName(i))) {
                                field.set(t, rs.getString(i));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return tClass.cast(t);
    }
}
