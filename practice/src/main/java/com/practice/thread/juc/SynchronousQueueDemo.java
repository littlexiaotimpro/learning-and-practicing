package com.practice.thread.juc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步阻塞队列应用
 * <p>
 * 同步阻塞队列，SynchronousQueue没有容量，与其他BlockingQueue不同，
 * SynchronousQueue是一个不存储元素的BlockingQueue，
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然
 *
 * @date 2022/2/21
 */
public class SynchronousQueueDemo {

    static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                long starTime = System.currentTimeMillis();
                queue.put("【msg】");
                long endTime = System.currentTimeMillis();
                System.out.printf("[%s,%s,take耗时:%s],%s%n", starTime, endTime, (endTime - starTime), Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //休眠5秒之后，从队列中take一个元素
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + "调用take获取并移除元素," + queue.take());
    }
}
