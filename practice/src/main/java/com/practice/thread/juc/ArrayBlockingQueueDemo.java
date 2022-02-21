package com.practice.thread.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 数据类型的阻塞队列应用
 * <p>
 * 基于数组的阻塞队列实现，其内部维护一个定长的数组，用于存储队列元素。
 * 线程阻塞的实现是通过ReentrantLock来完成的，数据的插入与取出共用同一个锁，
 * 因此ArrayBlockingQueue并不能实现生产、消费同时进行。
 * 而且在创建ArrayBlockingQueue时，我们还可以控制对象的内部锁是否采用公平锁，默认采用非公平锁。
 *
 * @date 2022/2/21
 */
public class ArrayBlockingQueueDemo {

    static ArrayBlockingQueue<String> pushQueue = new ArrayBlockingQueue<>(10000);
    // 验证队列空或满时，add、remove等操作
    static ArrayBlockingQueue<String> fullQueue = new ArrayBlockingQueue<>(1);

    static {
        new Thread(() -> {
            while (true) {
                String msg;
                try {
                    long starTime = System.currentTimeMillis();
                    // 获取一条推送消息，此方法会进行阻塞，直到返回结果
                    msg = pushQueue.take();
                    try {
                        // 队列为空时，执行remove抛异常：NoSuchElementException
                        String remove = fullQueue.remove();
                        System.out.printf("【fullQueue】，移除元素【%s】\n", remove);
                    } catch (Exception e) {
                        System.out.println("【fullQueue】，队列已空，无法继续【remove】");
                    }
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.printf("[%s,%s,take耗时:%s],%s,发送消息:%s%n", starTime, endTime, (endTime - starTime), Thread.currentThread().getName(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
    public static void pushMsg(String msg) throws InterruptedException {
        pushQueue.put(msg);
        try {
            // 队列满时，执行add操作抛异常：IllegalStateException
            fullQueue.add(msg);
        } catch (Exception e) {
            System.out.println("【fullQueue】，队列已满，无法继续【add】");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            String msg = "msg【" + i + "】";
            //模拟耗时
            TimeUnit.SECONDS.sleep(i);
            ArrayBlockingQueueDemo.pushMsg(msg);
        }
    }
}
