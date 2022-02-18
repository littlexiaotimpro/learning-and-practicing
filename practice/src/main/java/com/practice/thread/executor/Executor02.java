package com.practice.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池应用测试
 * <p>
 * target: 同步阻塞队列的使用
 *
 * @date 2022/2/18
 */
public class Executor02 {

    private final static Logger log = LoggerFactory.getLogger(Executor02.class);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 20,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        // 如下创建的线程池和上述构造器创建的线程池都是用的阻塞队列作为工作队列，一个不存储元素的阻塞队列
        // ExecutorService executor = Executors.newCachedThreadPool();
        log.info("第一批任务");
        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(() -> {
                //模拟任务内部处理耗时
                try {
                    // 第一批任务都休眠3s，新创建的任务丢入同步队列会失败，会尝试新建线程处理任务
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程【{}】，处理【{}】完毕", Thread.currentThread().getName(), taskName);
            });
        }
        // 主线程休眠5s，确保第一批任务完成
        TimeUnit.SECONDS.sleep(5);
        log.info("第二批任务");
        // 使用存活的线程处理任务
        for (int i = 0; i < 3; i++) {
            String taskName = "任务" + i;
            executor.execute(() -> {
                // 第二批任务在第一批任务的基础上直接使用现有的线程（在存活时间内的）
                log.info("线程【{}】，处理【{}】完毕", Thread.currentThread().getName(), taskName);
            });
        }
        executor.shutdown();
        log.info("关闭线程池");
    }

}
