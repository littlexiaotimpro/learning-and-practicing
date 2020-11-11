package com.practice.algorithm.primarykey;

/**
 * @author XiaoSi
 * @className Concurrent
 * @description 多线程并发执行序列号生成算法
 * @date 2020/11/10
 */
public class Concurrent {

    private void invokeGenerateId(String tName, long dataCenterId, long machineId) {
        SnowFlake snowFlake = new SnowFlake(dataCenterId, machineId);
        for (int i = 0; i < (1 << 4); i++) {
            long id = snowFlake.generateId(tName);
//            System.out.println(id);
        }
    }


    public static void main(String[] args) {
        Concurrent concurrent = new Concurrent();

        // 主线程
        System.out.println("主线程的执行方法");
        // 自定义线程
        class InnerThread extends Thread {
            @Override
            public void run() {
                System.out.println("自定义的继承线程类的执行方法");
                concurrent.invokeGenerateId(currentThread().getName(),1, 4);
            }
        }
        InnerThread innerThread = new InnerThread();
        // 自定义线程
        class InnerRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("自定义的实现接口的执行方法");
                concurrent.invokeGenerateId(Thread.currentThread().getName(),5, 6);
            }
        }
        Thread thread = new Thread(new InnerRunnable());

        // 执行方法
        concurrent.invokeGenerateId(Thread.currentThread().getName(),2, 3);
        innerThread.start();
        thread.start();
    }
}
