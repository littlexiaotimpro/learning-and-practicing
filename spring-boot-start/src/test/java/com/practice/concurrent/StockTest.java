package com.practice.concurrent;

import com.practice.start.StartApplication;
import com.practice.start.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {StartApplication.class})
public class StockTest {

    private final Logger logger = LoggerFactory.getLogger(StockTest.class);

    @Autowired
    private StockService stockService;

    @Test
    public void test() {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                logger.info(Thread.currentThread().getName() + " -> 线程池创建的执行线程");
                Map<String, Integer> params = new HashMap<>();
                params.put("goodId", 2);
                params.put("count", 2);
                stockService.handleStock(params);
            });
        }

        Map<String, Integer> params = new HashMap<>();
        params.put("goodId", 2);
        params.put("count", 2);
        stockService.handleStock(params);

//        Thread thread = new Thread(()->{
//            logger.info(Thread.currentThread().getName() + " -> 实现接口创建的执行线程");
//        });
//        thread.start();
    }

}
