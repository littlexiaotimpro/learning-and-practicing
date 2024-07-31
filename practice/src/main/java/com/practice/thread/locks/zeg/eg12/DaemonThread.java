package com.practice.thread.locks.zeg.eg12;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程测试
 *
 * @author chen.hong
 * @date 2022/3/15
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread dt = new Thread(() -> {
            System.out.printf("线程【%s】执行\n", Thread.currentThread().getName());
        }, "用户线程内部的守护线程");
        Thread ut = new Thread(() -> {
            System.out.printf("线程【%s】执行\n", Thread.currentThread().getName());
            dt.setDaemon(true);
            dt.start();
        }, "用户线程");
        ut.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(ut.getName() + ": " + ut.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(dt.getName() + ": " + dt.getState());
        TimeUnit.SECONDS.sleep(3);
    }

}
