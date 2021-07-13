package com.practice.thread.base.threadmethod;

import java.util.Calendar;
import java.util.Date;

/**
 * 线程方法
 *
 * @see Thread#join()
 * @see Thread#wait()
 * @see Thread#sleep(long)
 */
public class JoinMain {

    /**
     * 子线程顺序执行
     */
    private void millisWithZero() {
        Thread thread1 = new Thread(() -> {
            System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(),
                    Thread.currentThread().getThreadGroup().getName()));
        }, "sub-thread-1");

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        Thread.currentThread().getThreadGroup().getName()));
            }
        }, "sub-thread-2");

        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        Thread.currentThread().getThreadGroup().getName()));
            }
        }, "sub-thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 子线程顺序执行
     */
    private void withMillis(long millis) {
        Thread thread4 = new Thread(this::show, "sub-thread-4");

        Thread thread5 = new Thread(() -> {
            try {
                thread4.join(3000);
                Calendar.Builder builder = new Calendar.Builder();
                Calendar calendar = builder.setInstant(new Date()).build();
                int s = calendar.get(Calendar.SECOND);
                int r = s + 1;
                while (s < r) {
                    show();
                    s = builder.setInstant(new Date()).build().get(Calendar.SECOND);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sub-thread-5");

        Thread thread6 = new Thread(() -> {
            try {
                thread5.join(millis);
                show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sub-thread-6");

        thread4.start();
        thread5.start();
        thread6.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread4.getName() + " -> " + thread4.getState());
        System.out.println(thread5.getName() + " -> " + thread5.getState());
        System.out.println(thread6.getName() + " -> " + thread6.getState());
    }

    private synchronized void show() {
        System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                Thread.currentThread().getName(),
                Thread.currentThread().getPriority(),
                Thread.currentThread().getThreadGroup().getName()));
    }

    public static void main(String[] args) throws InterruptedException {
//        JoinMain joinMain = new JoinMain();
//        joinMain.millisWithZero();
//        joinMain.withMillis(2000);
        Thread a = new Thread(() -> {
            System.out.println("执行A");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a");
        Thread b = new Thread(() -> {
            System.out.println("执行B");
        }, "b");
        a.start();
        // 当a线程执行完成后，main线程继续执行
        a.join();
        //
        a.join(1000);
        // b线程的状态存在多种可能
        b.start();
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }
}
