package com.practice.thread.juc;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 优先阻塞队列应用
 * <p>
 * 一个支持优先级排序的无界阻塞队列，进入队列的元素会按照优先级进行排序
 *
 * @date 2022/2/21
 */
public class PriorityBlockingQueueDemo {
    private static class Msg implements Comparable<Msg> {
        // 优先级，越小优先级越高
        private final int priority;
        private final String msg;

        public Msg(int priority, String msg) {
            this.priority = priority;
            this.msg = msg;
        }

        @Override
        public int compareTo(Msg o) {
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    //推送队列
    static PriorityBlockingQueue<Msg> pushQueue = new PriorityBlockingQueue<>();

    static {
        //启动一个线程做真实推送
        new Thread(() -> {
            while (true) {
                Msg msg;
                try {
                    long starTime = System.currentTimeMillis();
                    //获取一条推送消息，此方法会进行阻塞，直到返回结果
                    msg = pushQueue.take();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(100);
                    long endTime = System.currentTimeMillis();
                    System.out.printf("[%s,%s,take耗时:%s],%s,发送消息:%s%n", starTime, endTime, (endTime - starTime), Thread.currentThread().getName(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
    public static void pushMsg(int priority, String msg) {
        pushQueue.put(new Msg(priority, msg));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 5; i >= 1; i--) {
            String msg = "msg【" + i + "】";
            pushMsg(i, msg);
        }
    }
}
