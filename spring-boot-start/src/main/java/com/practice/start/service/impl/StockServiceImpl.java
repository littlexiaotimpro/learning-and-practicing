package com.practice.start.service.impl;

import com.practice.start.primary.SnowFlake;
import com.practice.start.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 库存服务接口实现
 */
@Service
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void handleStock(Map<String, Integer> params) {
        this.subtract(params);
    }

    @Override
    public void subtract(Map<String, Integer> params) {
        String sql = "update tb_goods set stock=stock-? where goodID=?";
        Integer goodId = params.get("goodId");
        if (isLessThenZero(goodId)) {
            logger.error("库存不足，无法下单！");
        } else {
            Integer count = params.get("count");
            jdbcTemplate.update(sql, count, goodId);
            this.increase(params);
            logger.info("库存充足，下单成功！");
        }
    }

    private boolean isLessThenZero(Integer... id) {
        String sql = "select stock from tb_goods where goodID=?";
        AtomicBoolean res = new AtomicBoolean(false);
        jdbcTemplate.query(sql, id, rs -> {
            int anInt = rs.getInt(1);
            if (anInt <= 0) {
                res.set(true);
            }
        });
        return res.get();
    }

    @Override
    public void increase(Map<String, Integer> params) {
        String sql = "insert into tb_orders values(?,?,0,?)";
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String tName = Thread.currentThread().getName();
        long id = snowFlake.generateId(tName);
        Integer goodId = params.get("goodId");
        Integer count = params.get("count");
        jdbcTemplate.update(sql, id, goodId, count);
    }

    @Override
    public void isConsistent() {
        String sql = "select stock from tb_goods where goodId=1";
        Integer stock = jdbcTemplate.queryForObject(sql, Integer.class);
        sql = "select sum(goodsCount) from tb_orders";
        Integer orderCount = jdbcTemplate.queryForObject(sql, Integer.class);
        if (stock == null) {
            logger.info("商品暂无库存");
        } else {
            orderCount = Optional.ofNullable(orderCount).orElse(0);
            if (stock + orderCount == 50) {
                logger.info("结果一致");
            } else {
                logger.error("结果不一致");
            }
        }
    }
}
