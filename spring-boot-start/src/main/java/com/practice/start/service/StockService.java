package com.practice.start.service;

import java.util.Map;

/**
 * 库存服务
 */
public interface StockService {


    void handleStock(Map<String, Integer> params);

    /**
     * 减库存
     */
    void subtract(Map<String, Integer> params);

    /**
     * 增加库存
     */
    void increase(Map<String, Integer> params);

    /**
     * 结果一致性验证
     */
    void isConsistent();

}
