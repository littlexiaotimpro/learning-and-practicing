package com.practice.service;

/**
 * @ClassName GenericService
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/1112:58
 */
public interface GenericService<T> {
    void testGeneric(T t);

    /**
     * default
     * 修饰的方法可以在接口中有具体实现
     * 同时实现类内部可以重写此方法
     */
//    default void testDefault() {
//    }

}
