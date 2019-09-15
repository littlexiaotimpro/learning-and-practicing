package com.practice.utils.convert;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用泛型实现对象转换
 */
@Slf4j
@Accessors(chain = true)
@Data
public class ConvertUtil<A, B> {
    private A a;
    private B b;

    public void abTest() {
        log.info("数据类型：A -> [{}]，B -> [{}]，结果值：A -> [{}]，B -> [{}]", a.getClass().getSimpleName(), b.getClass().getSimpleName(), a, b);
    }

    public A show(A a) throws Exception {
        Class<?> clazz = a.getClass();
        Object object = clazz.newInstance();
        return a;
    }

    public static <T> T showMessage(T t) {
        log.info("数据类型：[{}]，结果值：[{}]", t.getClass().getSimpleName(), t);
        return t;
    }
}
