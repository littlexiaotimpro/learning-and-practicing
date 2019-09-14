package com.practice.utils.convert;

import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 使用泛型实现对象转换
 */
@Slf4j
@Accessors(chain = true)
@Data
public class ConvertUtil<A, B> {
    private A a;
    private B b;

    public A show(A a) throws Exception{
        Class<?> clazz = a.getClass();
        Object object = clazz.newInstance();
        return a;
    }

    public static <T> T showMessage(T t) {
        log.info("数据类型：[{}]，结果值：[{}]", t.getClass(), t);
        return t;
    }
}
