package com.practice.thread;

/**
 * 主线程
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 当前主线程设置休眠
        Thread.sleep(2000);

        // 创建线程对象
        OwnerThread ownerThread = new OwnerThread();
        OwnerRunnable ownerRunnable = new OwnerRunnable();
        Thread thread = new Thread(ownerRunnable);

        // 自定义线程的逻辑操作
        ownerThread.start();
        synchronized (ownerThread){
            ownerThread.wait(10000);
        }
        thread.start();
        System.out.println("主线程的执行方法");
    }
}
