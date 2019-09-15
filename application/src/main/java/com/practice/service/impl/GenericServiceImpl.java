package com.practice.service.impl;

import com.practice.service.GenericService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName GenericServiceImpl
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/1112:59
 */
@Slf4j
public class GenericServiceImpl<T> implements GenericService<T> {
    public void testGeneric(T t) {
        log.info("数据类型：[{}]，结果值：[{}]", t.getClass().getSimpleName(), t);
    }
}
