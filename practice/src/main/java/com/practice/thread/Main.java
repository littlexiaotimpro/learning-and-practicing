package com.practice.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程
 */
public class Main {

    private String b = "3";
    private static final String A = "1";

    public static void ta(){
        // 静态方法无法直接调用实例变量
        // b = "4";
    }

    private void te(){
        b = "4";
        String a = A;
    }

    public static void main(String[] args) throws InterruptedException {
        // 当前主线程设置休眠
        Thread.sleep(2000);

        // 创建线程对象
        OwnerThread ownerThread = new OwnerThread();
        OwnerRunnable ownerRunnable = new OwnerRunnable();
        Thread thread = new Thread(ownerRunnable);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(ownerRunnable);

        // 自定义线程的逻辑操作
        ownerThread.start();
        thread.start();
        System.out.println("主线程的执行方法");
        List<Runnable> runnables = executorService.shutdownNow();
        runnables.forEach(runnable -> {
            System.out.println(runnable.toString());
        });
    }
}
