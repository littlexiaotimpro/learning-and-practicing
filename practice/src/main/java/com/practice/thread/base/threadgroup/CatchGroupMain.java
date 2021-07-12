package com.practice.thread.base.threadgroup;

import java.util.stream.IntStream;

public class CatchGroupMain {

    /**
     * 线程组统一处理异常
     */
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("sub-group") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                super.uncaughtException(t, e);
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };
        System.out.println("当前线程组的父线程组：" + threadGroup.getParent().getName());
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(threadGroup, () -> {
                if ((i & 1) == 0) {
                    throw new RuntimeException("优先级为" + i + "的指数幂的抛异常！");
                }
                System.out.println(String.format("当前执行的线程是：%s，优先级：%d，线程组：%s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        Thread.currentThread().getThreadGroup().getName()));
            }, "sub-thread-" + i);
            thread.setPriority(i);
            thread.start();
        });
    }
}
