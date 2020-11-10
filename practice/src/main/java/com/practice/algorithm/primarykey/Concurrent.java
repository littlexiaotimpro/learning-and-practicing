package com.practice.algorithm.primarykey;

/**
 * @author XiaoSi
 * @className Concurrent
 * @description 多线程并发执行序列号生成算法
 * @date 2020/11/10
 */
public class Concurrent {

    private void invokeGenerateId() {
        SnowFlake snowFlake = new SnowFlake(2, 3);
        for (int i = 0; i < (1 << 3); i++) {
            long id = snowFlake.generateId();
//            System.out.println(id);
        }
    }


    public static void main(String[] args) {
        Concurrent concurrent = new Concurrent();

        // 主线程
        System.out.println("主线程的执行方法");
        concurrent.invokeGenerateId();

        // 自定义线程
        class InnerThread extends Thread {
            @Override
            public void run() {
                System.out.println("自定义的继承线程类的执行方法");
                concurrent.invokeGenerateId();
            }
        }
        InnerThread innerThread = new InnerThread();
        innerThread.start();

        // 自定义线程
        class InnerRunnable implements Runnable{
            @Override
            public void run() {
                System.out.println("自定义的实现接口的执行方法");
                concurrent.invokeGenerateId();
            }
        }
        Thread thread = new Thread(new InnerRunnable());
        thread.start();
    }
}
