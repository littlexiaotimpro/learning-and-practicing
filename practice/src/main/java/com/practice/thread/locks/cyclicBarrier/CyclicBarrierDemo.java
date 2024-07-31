package com.practice.thread.locks.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 应用
 * <p>
 * 不同于 CountDownLatch (简: CDL)，CDL是使一批线程等待另一批线程执行完后再执行
 * 而 CB 只是使等待的线程达到一定数目后再让它们继续执行
 *
 * @date 2022/2/22
 * @see CyclicBarrier
 */
public class CyclicBarrierDemo {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("人都到齐了，开始吧");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    int timeout = (int) (Math.random() * 10);
                    System.out.println(Thread.currentThread().getName() + ", 到了，休眠" + timeout + "s");
                    TimeUnit.SECONDS.sleep(timeout);
                    long starTime = System.currentTimeMillis();
                    cyclicBarrier.await();
                    long endTime = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + "等待了" + (endTime - starTime) + "(ms)");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
    }

}
