package com.practice.thread.locks.eg9;

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
public class CyclicBarrierDemo01 {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                long starTime = 0;
                try {
                    int timeout = (int) (Math.random() * 10);
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    System.out.println(Thread.currentThread().getName() + "到了");
                    starTime = System.currentTimeMillis();
                    // 响应中断后，线程【t5】抛出 InterruptedException，其他线程抛出异常 BrokenBarrierException，并继续执行
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + ", 等待了" + (endTime - starTime) + "(ms)，开始了");
            }, "t" + i);
            t.start();

            if (i == 5) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(t.getName() + "提议不用等了，直接开始吧！");
                t.interrupt();
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

}
