package com.practice.thread.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 应用测试
 *
 * @author chen.hong
 * @date 2022/2/21
 */
public class ThreadLocalDemo02 {

    private final static Logger log = LoggerFactory.getLogger(ThreadLocalDemo02.class);

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static final AtomicInteger atomic = new AtomicInteger(1);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3,
            5,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("test" + atomic.getAndIncrement());
                log.info("线程池创建线程【{}】", thread.getName());
                return thread;
            });

    private static void service(List<Integer> var0) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        log.info("【{}】，线程【{}】调用【service】执行业务", threadLocal.get(), Thread.currentThread().getName());
        doSomething(var0);
    }

    private static void doSomething(List<Integer> var0) {
        log.info("【{}】，线程【{}】调用【doSomething】执行业务", threadLocal.get(), Thread.currentThread().getName());
        for (Integer integer : var0) {
            new Thread(() -> {
                log.info("\n**************【{}】****************\n" +
                                "******【{}】，线程【{}】处理数据【{}】\n" +
                                "******【{}】，线程【{}】处理数据【{}】\n" +
                                "**************【{}】****************",
                        Thread.currentThread().getName(),
                        threadLocal.get(), Thread.currentThread().getName(), integer,
                        inheritableThreadLocal.get(), Thread.currentThread().getName(), integer,
                        Thread.currentThread().getName());
            }, "sub-test-" + integer).start();
        }
    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3);
        for (int i = 0; i < 10; i++) {
            String id = String.valueOf(i);
            executor.execute(() -> {
                threadLocal.set(id);
                inheritableThreadLocal.set(id);
                try {
                    service(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    threadLocal.remove();
                    inheritableThreadLocal.remove();
                }
            });
        }
        executor.shutdown();
        log.info("主线程关闭线程池！");
    }
}
