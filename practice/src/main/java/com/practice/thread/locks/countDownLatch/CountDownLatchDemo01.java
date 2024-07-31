package com.practice.thread.locks.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 应用
 * <p>
 * 构建两个线程计数器，总计总耗时
 * - 所有线程都在第一个计数器上等待
 * - 主线程调用countDown后，唤醒所有线程，所有线程开始继续执行，输出对应执行时间
 *
 * @date 2022/2/22
 * @see CountDownLatch
 */
public class CountDownLatchDemo01 {

    static class IThread extends Thread {

        private final CountDownLatch c1;
        private final CountDownLatch c2;

        public IThread(String name, CountDownLatch c1, CountDownLatch c2) {
            super(name);
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public void run() {
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long startTime = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(((int) (Math.random() * 10)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                c2.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.printf("线程【%s】，耗时【%dms】\n", this.getName(), endTime - startTime);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始");
        int threadSize = 10;
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(threadSize);
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < threadSize; i++) {
            String tName = "t" + i;
            IThread iThread = new IThread(tName, c1, c2);
            iThread.start();
        }
        TimeUnit.SECONDS.sleep(2);
        c1.countDown();
        // 线程计数为0的时候，自动唤醒主线程，并继续
        c2.await();
        System.out.println("主线程结束");
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - starTime));
    }
}
