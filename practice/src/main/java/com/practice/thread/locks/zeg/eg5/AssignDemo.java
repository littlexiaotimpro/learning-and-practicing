package com.practice.thread.locks.zeg.eg5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化通信
 */
public class AssignDemo {
    public static void main(String[] args) {
        Source source = new Source();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.invokeA(i + 1);
            }
        }, "TD-A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.invokeB(i + 1);
            }
        }, "TD-B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.invokeC(i + 1);
            }
        }, "TD-C").start();
    }
}

/**
 * 资源类
 */
class Source {
    private int flag = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition ca = lock.newCondition();
    private final Condition cb = lock.newCondition();
    private final Condition cc = lock.newCondition();

    public void invokeA(int c) {
        lock.lock();
        try {
            while (flag != 1) {
                ca.await();
            }
            Thread thread = Thread.currentThread();
            System.out.printf("线程: %s, number: %d\n", thread.getName(), c);
            flag = 2;
            cb.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void invokeB(int c) {
        lock.lock();
        try {
            while (flag != 2) {
                cb.await();
            }
            Thread thread = Thread.currentThread();
            System.out.printf("线程: %s, number: %d\n", thread.getName(), c);
            flag = 3;
            cc.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void invokeC(int c) {
        lock.lock();
        try {
            while (flag != 3) {
                cc.await();
            }
            Thread thread = Thread.currentThread();
            System.out.printf("线程: %s, number: %d\n", thread.getName(), c);
            flag = 1;
            ca.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
