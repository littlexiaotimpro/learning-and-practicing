package com.practice.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池应用测试
 * <p>
 * target: 线程池基本使用
 *
 * @date 2022/2/18
 */
public class Executor01 {

    private final static Logger log = LoggerFactory.getLogger(Executor01.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() -> {
                //模拟任务内部处理耗时
                try {
                    TimeUnit.SECONDS.sleep(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程【{}】，处理【{}】完毕", Thread.currentThread().getName(), taskName);
            });
        }
        executor.shutdown();
        log.info("关闭线程池");
    }

}
