package com.practice.thread.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CAS 测试
 *
 * @date 2022/2/21
 */
public class CASDemo {

    private final static Logger log = LoggerFactory.getLogger(CASDemo.class);

    private static volatile int count = 0;

    /**
     * 比较并交换
     *
     * @param expect 预期值
     * @param update 更新值
     * @return 【true, 新值替换】
     */
    private synchronized boolean compareAndSwap(int expect, int update) {
        log.info("线程【{}】获取计数，当前值【{}】", Thread.currentThread().getName(), count);
        if (count == expect) {
            count = update;
            return true;
        }
        return false;
    }

    private void increase() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
        int e;
        do {
            e = count;
        } while (!compareAndSwap(e, e + 1));
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        CASDemo casDemo = new CASDemo();
        /*
         * CountDownLatch称之为闭锁，它可以使一个或一批线程在闭锁上等待，
         * 等到其他线程执行完相应操作后，闭锁打开，这些等待的线程才可以继续执行。
         * 确切的说，闭锁在内部维护了一个倒计数器。
         * 通过该计数器的值来决定闭锁的状态，从而决定是否允许等待的线程继续执行。
         */
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    casDemo.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 一个线程执行玩，让计数器减1
                    countDownLatch.countDown();
                }
            }).start();
        }
        // 调用await()会让当前线程等待，直到计数器为0的时候，方法才会返回，此方法会响应线程中断操作。
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        log.info("线程【{}】, 耗时【{}】ms, count最终值为【{}】", Thread.currentThread().getName(), endTime - starTime, count);
    }

}
