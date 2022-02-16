package com.practice.thread.base.threadgroup;

import java.util.stream.IntStream;

public class GroupMain {

    /**
     * ThreadGroup管理着它下面的Thread，
     * ThreadGroup是一个标准的向下引用的树状结构，
     * 这样设计的原因是防止"上级"线程被"下级"线程引用而无法有效地被GC回收。
     */
    public static void main(String[] args) {
        System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                Thread.currentThread().getName(),
                Thread.currentThread().getPriority(),
                Thread.currentThread().getThreadGroup().getName()));
        /*
         * 线程的执行并不会严格按照线程优先级的顺序执行（与线程的调度策略有关）
         *
         * Java提供一个线程调度器来监视和控制处于RUNNABLE状态的线程。
         * 线程的调度策略采用抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。
         * 在优先级相同的情况下，按照“先到先得”的原则。
         * 每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程。
         *
         * 守护线程（如，GC）的默认优先级相对较低
         * 若程序中的所有非守护线程执行结束，则守护线程自动结束
         */
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(() -> {
                System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        Thread.currentThread().getThreadGroup().getName()));
            }, "a-sub-thread-" + i);
            thread.setPriority(i);
            thread.start();
        });

        /*
         * 修改线程组的最大优先级后，组内线程的最高优先级不会超过该值
         * 若大于该值，会以线程组的最大优先级作为线程的优先级
         */
        ThreadGroup threadGroup = new ThreadGroup("self");
        threadGroup.setMaxPriority(6);
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(threadGroup, () -> {
                System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s，父线程组：%s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        Thread.currentThread().getThreadGroup().getName(),
                        threadGroup.getParent().getName()));
            }, "b-sub-thread-" + i);
            thread.setPriority(i);
            thread.start();
        });
    }
}
