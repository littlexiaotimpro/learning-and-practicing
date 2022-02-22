package com.practice.thread.locks.eg10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 应用
 *
 * @date 2022/2/22
 * @see CountDownLatch
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        long starTime = System.currentTimeMillis();
        for (int i = 0; i < threadSize; i++) {
            String tName = "t" + i;
            String msg = String.valueOf(i);
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                    System.out.printf("线程【%s】输出信息【msg%s】\n", Thread.currentThread().getName(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 计数 -1
                    countDownLatch.countDown();
                }
            }, tName).start();
        }
        // 线程计数为0的时候，自动唤醒主线程，并继续
        // countDownLatch.await();
        // 到达超时时限时线程自动唤醒，并继续，计数
        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println("主线程结束");
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - starTime) + ", await: " + await);
    }
}
