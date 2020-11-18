package com.practice.test.annotation;

import java.lang.annotation.*;

/**
 * 主键标识
 */
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {
}
