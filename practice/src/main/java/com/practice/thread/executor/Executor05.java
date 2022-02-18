package com.practice.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池应用测试
 * <p>
 * target: 线程池扩展操作
 *
 * @date 2022/2/18
 */
public class Executor05 {

    private final static Logger log = LoggerFactory.getLogger(Executor05.class);

    public static void main(String[] args) throws InterruptedException {
        // 自定义工厂及饱和策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                10L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                IThreadFactory.iThreadFactory,
                IRejectedExecutionHandler.iRejectedExecutionHandler) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                log.info("线程【{}】执行【{}】前", t.getName(), r.toString());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                log.info("线程【{}】执行【{}】后，可能抛出异常【{}】", Thread.currentThread().getName(), r.toString(), t);
            }

            @Override
            protected void terminated() {
                log.info("线程【{}】关闭线程池", Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //模拟任务内部处理耗时
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("线程【{}】处理【{}】完毕", Thread.currentThread().getName(), taskName);
                }

                @Override
                public String toString() {
                    return taskName;
                }
            });
        }
        TimeUnit.SECONDS.sleep(3);
        executor.shutdown();
        log.info("关闭线程池");
    }

    private static class IThreadFactory implements ThreadFactory {
        private final static Logger log = LoggerFactory.getLogger(IThreadFactory.class);
        private final static IThreadFactory iThreadFactory = new IThreadFactory();

        @Override
        public Thread newThread(Runnable r) {
            log.info("自定义线程工厂创建线程任务【{}】", r.toString());
            return new Thread(r);
        }
    }

    private static class IRejectedExecutionHandler implements RejectedExecutionHandler {
        private final static Logger log = LoggerFactory.getLogger(IRejectedExecutionHandler.class);
        private final static IRejectedExecutionHandler iRejectedExecutionHandler = new IRejectedExecutionHandler();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 自定义饱和策略，记录一下无法处理的任务
            log.warn("无法处理的任务【{}】", r.toString());
        }
    }

}
