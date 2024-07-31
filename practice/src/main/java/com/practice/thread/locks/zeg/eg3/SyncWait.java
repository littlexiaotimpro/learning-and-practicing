package com.practice.thread.locks.zeg.eg3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncWait {
    public static void main(String[] args) {
        Source source = new Source();
        SourceLock sourceLock = new SourceLock();
        for (int i = 0; i < 4; i++) {
            Thread thread;
            if (i < 2) {
                thread = new Thread(() -> {
                    try {
                        for (int j = 0; j < 8; j++) {
                            source.add();
                            sourceLock.subtract();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                thread = new Thread(() -> {
                    try {
                        for (int j = 0; j < 8; j++) {
                            source.subtract();
                            sourceLock.add();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            thread.setName("TD-" + (i + 1));
            thread.start();
        }
    }
}

class Source {

    private int number = 0;

    public synchronized void add() throws InterruptedException {
        /*
         * 虚假唤醒：
         * 当一个正在等待条件变量的线程由于条件变量被触发而唤醒时，
         * 却发现它等待的条件（共享数据）没有满足(也就是没有共享数据)。
         *
         * if -> while：被唤醒的线程，循环检查自己是否真的满足了唤醒条件，若不满足，继续等待
         */
        while (number != 0) {
            // 在哪里等待，在哪里唤醒
            this.wait();
        }
        number++;
        final Thread thread = Thread.currentThread();
        System.out.printf("synchronized, 线程: %s, number: %d\n", thread.getName(), number);
        this.notifyAll();
    }

    public synchronized void subtract() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        final Thread thread = Thread.currentThread();
        System.out.printf("synchronized, 线程: %s, number: %d\n", thread.getName(), number);
        this.notifyAll();
    }
}

class SourceLock {
    private int number = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            /*
             * 虚假唤醒：
             * 当一个正在等待条件变量的线程由于条件变量被触发而唤醒时，
             * 却发现它等待的条件（共享数据）没有满足(也就是没有共享数据)。
             *
             * if -> while：被唤醒的线程，循环检查自己是否真的满足了唤醒条件，若不满足，继续等待
             */
            while (number != 0) {
                // 在哪里等待，在哪里唤醒
                condition.await();
            }
            number++;
            final Thread thread = Thread.currentThread();
            System.out.printf("lock, 线程: %s, number: %d\n", thread.getName(), number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void subtract() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            final Thread thread = Thread.currentThread();
            System.out.printf("lock, 线程: %s, number: %d\n", thread.getName(), number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
