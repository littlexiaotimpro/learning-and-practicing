package com.practice.thread.locks.eg11;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 对比 HashMap 和 ConcurrentHashMap
 *
 * @date 2022/2/28
 */
public class ConcurrentHashMapDemo {

    /**
     * 首先HashMap是线程不安全的，其主要体现：
     * <p> 在 jdk1.7 中，在多线程环境下，扩容时会造成环形链或数据丢失。
     * <p> 在 jdk1.8 中，在多线程环境下，会发生数据覆盖的情况。
     */
    private static void hashMap() throws BrokenBarrierException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    TimeUnit.MILLISECONDS.sleep(((int) (Math.random() * 10)) * 1000L);
                    for (int i1 = 0; i1 < 10; i1++) {
                        String key = "k" + i1;
                        String value = "v" + i1 + "-" + Thread.currentThread().getName();
                        map.put(key, value);
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        TimeUnit.SECONDS.sleep(6);
        System.out.println(map);
    }

    /**
     * ConcurrentHashMap 读和写没有使用读写锁进行互斥，也不能保证强一致性
     */
    private static void concurrentHashMap() throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(((int) (Math.random() * 10)) * 1000L);
                    for (int i1 = 0; i1 < 10; i1++) {
                        String key = "k" + i1;
                        String value = "v" + i1 + "-" + Thread.currentThread().getName();
                        map.put(key, value);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }, "t" + i).start();
        }
        countDownLatch.await();
        System.out.println(map);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                hashMap();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                concurrentHashMap();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
