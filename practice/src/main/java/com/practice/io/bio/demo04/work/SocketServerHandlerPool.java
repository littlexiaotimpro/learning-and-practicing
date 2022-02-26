package com.practice.io.bio.demo04.work;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * @date 2022/2/26
 */
public class SocketServerHandlerPool {

    // 创建线程池对象
    private ExecutorService executorService;

    public SocketServerHandlerPool(int maxThreadSize, int queueCapacity) {
        executorService = new ThreadPoolExecutor(
                2,
                maxThreadSize,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity));
    }

    public void execute(Runnable r) {
        executorService.execute(r);
    }
}
