package com.practice.test.dao;

public abstract class AbstractDataAccessor {

    /**
     * 新增数据
     * TODO 单量，及批量插入
     *
     * @return 操作结果
     */
    public abstract int insert(String sql, Object... args);

    /**
     * 删除数据
     * TODO 单量，及批量删除
     *
     * @return 操作结果
     */
    public abstract int delete(String sql, Object... args);
    public abstract <T> int delete(T t);

    /**
     * 修改数据
     * TODO 单量，及批量修改
     *
     * @return 操作结果
     */
    public abstract int update(String sql, Object... args);

    /**
     * 查询数据
     * TODO 单个对象，集合对象
     *
     * @return 操作结果
     */
    public abstract <T> T select(Class<T> tClass, String sql, Object... args) throws Exception;

}
