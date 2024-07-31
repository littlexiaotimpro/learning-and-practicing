package com.practice.thread.locks.zeg.eg4;

/**
 * 死锁
 * jps：查看 java 进程，找到发生死锁的进程 ID
 * jstack id：查看死锁进程的相关日志
 */
public class DeadLock {

    public static void main(String[] args) {
        final Source source = new Source();
        final Thread thread1 = new Thread(source::toGetLock2, "T-1");
        final Thread thread2 = new Thread(source::toGetLock1, "T-2");
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class Source {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void toGetLock2() {
        synchronized (lock1) {
            final Thread thread = Thread.currentThread();
            System.out.printf("线程: %s\n", thread.getName());
            try {
                // 线程睡眠，但不会释放资源
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.printf("线程: %s, 尝试获取资源锁2\n", thread.getName());
            }
        }
    }

    public void toGetLock1() {
        synchronized (lock2) {
            final Thread thread = Thread.currentThread();
            System.out.printf("线程: %s\n", thread.getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.printf("线程: %s, 尝试获取资源锁1\n", thread.getName());
            }
        }
    }

}