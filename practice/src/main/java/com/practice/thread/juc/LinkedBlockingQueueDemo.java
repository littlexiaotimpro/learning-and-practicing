package com.practice.thread.juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 链式阻塞队列应用
 * <p>
 * 基于单向链表的阻塞队列实现，在初始化LinkedBlockingQueue的时候可以指定大小，也可以不指定，
 * 默认类似一个无限大小的容量（Integer.MAX_VALUE），不指队列容量大小也是会有风险的，
 * 一旦数据生产速度大于消费速度，系统内存将有可能被消耗殆尽，因此要谨慎操作。
 * 另外LinkedBlockingQueue中用于阻塞生产者、消费者的锁是两个（锁分离），因此生产与消费是可以同时进行的。
 *
 * @date 2022/2/21
 */
public class LinkedBlockingQueueDemo {

    static LinkedBlockingQueue<String> pushQueue = new LinkedBlockingQueue<>(100);

    /**
     * 消费消息
     */
    private static void consumer() {
        new Thread(() -> {
            while (true) {
                String msg;
                try {
                    long starTime = System.currentTimeMillis();
                    // 获取一条推送消息，此方法会进行阻塞，直到返回结果
                    msg = pushQueue.take();
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.printf("[%s,%s,take耗时:%s],%s,消费消息:%s%n", starTime, endTime, (endTime - starTime), Thread.currentThread().getName(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 生成消息
     */
    private static void producer() {
        for (int i = 0; i < 5; i++) {
            String msg = "msg【" + i + "】";
            new Thread(() -> {
                long starTime = System.currentTimeMillis();
                try {
                    TimeUnit.SECONDS.sleep(5);
                    pushQueue.put(msg);
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    System.out.printf("[%s,%s,take耗时:%s],%s,发送消息:%s%n", starTime, endTime, (endTime - starTime), Thread.currentThread().getName(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        consumer();
        producer();
    }
}
