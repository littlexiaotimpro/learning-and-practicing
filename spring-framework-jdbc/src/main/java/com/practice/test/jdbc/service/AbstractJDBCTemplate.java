package com.practice.test.jdbc.service;

/**
 * @author XiaoSi
 * @className AbstractJDBCTemplate
 * @description 抽象模板方法
 * @date 2020/5/23
 */
public abstract class AbstractJDBCTemplate {

    /**
     * 新增数据
     * TODO 单量，及批量插入
     * @return 操作结果
     */
    public abstract int insert();

    /**
     * 删除数据
     * TODO 单量，及批量删除
     * @return 操作结果
     */
    public abstract int delete();

    /**
     * 修改数据
     * TODO 单量，及批量修改
     * @return 操作结果
     */
    public abstract int update();

    /**
     * 查询数据
     * TODO 单个对象，集合对象
     * @return 操作结果
     */
    public abstract <T> T select(Class<T> tClass,String sql, String ...args);

}
