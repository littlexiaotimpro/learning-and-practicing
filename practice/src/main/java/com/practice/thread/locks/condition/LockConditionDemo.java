package com.practice.thread.locks.condition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * m个线程交替打印，并重复n次
 *
 * @author xiaosi
 * @date 2024/7/31
 * @since 1.0
 */
public class LockConditionDemo {

    private static final List<String> LIST = Arrays.asList("X", "Y", "Z");

    private static int count = 0;

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        Thread ta = new Thread(() -> {
            try {
                lock.lock();
                for (int i1 = 0; i1 < 10; i1++) {
                    while (count % 3 != 0) {
                        a.await();
                    }
                    System.out.print(LIST.get(0));
                    count++;
                    b.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程" + LIST.get(0));

        Thread tb = new Thread(() -> {
            try {
                lock.lock();
                for (int i1 = 0; i1 < 10; i1++) {
                    while (count % 3 != 1) {
                        b.await();
                    }
                    System.out.print(LIST.get(1));
                    count++;
                    c.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程" + LIST.get(1));

        Thread tc = new Thread(() -> {
            try {
                lock.lock();
                for (int i1 = 0; i1 < 10; i1++) {
                    while (count % 3 != 2) {
                        c.await();
                    }
                    System.out.println(LIST.get(2));
                    count++;
                    a.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程" + LIST.get(2));

        ta.start();
        tb.start();
        tc.start();
    }


}
