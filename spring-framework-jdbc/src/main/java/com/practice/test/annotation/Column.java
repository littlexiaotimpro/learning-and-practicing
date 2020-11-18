package com.practice.test.annotation;

import java.lang.annotation.*;

/**
 * JDBCTemplate 的数据库字段及实体属性的映射
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value() default "";
}
