package com.practice.start.controller;

import com.practice.start.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 并发条件下库存控制器
 */
@RestController
@RequestMapping(value = "/order")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping(value = "/stock")
    public void pay(@RequestBody Map<String, Integer> params) throws Exception {
        stockService.handleStock(params);
        throw new Exception("测试异常父类");
    }

    @GetMapping(value = "/consistent")
    public void isConsistent(){
        stockService.isConsistent();
        throw new NullPointerException("空指针异常");
    }

    @GetMapping(value = "/aspect")
    public String aspect(){
        return "Aspect";
    }

}
