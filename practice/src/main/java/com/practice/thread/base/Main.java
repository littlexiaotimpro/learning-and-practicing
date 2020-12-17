package com.practice.thread.base;

import java.util.List;
import java.util.concurrent.*;

/**
 * 主线程
 */
public class Main {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // 当前主线程设置休眠
        Thread.sleep(2000);

        // 自定义线程
        class SelfThread extends Thread {
            @Override
            public void run() {
                threadLocal.set("继承: " + currentThread().getName());
                System.out.println(threadLocal.get());
            }
        }

        Runnable rs = () -> {
            threadLocal.set("实现: " + Thread.currentThread().getName());
            System.out.println(threadLocal.get());
        };

        // 创建线程对象
        SelfThread selfThread = new SelfThread();
        Thread thread = new Thread(rs);
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                String name = Thread.currentThread().getName();
                if (name.endsWith("3")){
                    throw new RuntimeException("多线程的异常测试！");
                }
                threadLocal.set("线程池: " + name);
                System.out.println(threadLocal.get());
            });
        }

        // 自定义线程的逻辑操作
        selfThread.start();
        thread.start();
        List<Runnable> runnables = executorService.shutdownNow();
        runnables.forEach(runnable -> {
            System.out.println(runnable.toString());
        });
        threadLocal.remove();
    }
}
