package com.practice.thread.aqs.lock;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

/**
 * AQS 自实现公平锁
 *
 * @date 2022/2/25
 */
public class AQSDemoLock {
    // 获取锁的状态
    private volatile int state = 0;
    // 获取锁的线程
    private Thread threadHolder;
    // 等待队列（非阻塞队列）
    private final ConcurrentLinkedDeque<Thread> waiters = new ConcurrentLinkedDeque<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getThreadHolder() {
        return threadHolder;
    }

    public void setThreadHolder(Thread threadHolder) {
        this.threadHolder = threadHolder;
    }

    public void lock() {
        // 尝试获取锁，获取成功直接返回，不成功进入等待队列
        if (tryAcquire()) {
            return;
        }
        Thread currentThread = Thread.currentThread();
        // 未获取到锁的线程，进入等待队列
        ConcurrentLinkedDeque<Thread> waiter = this.waiters;
        waiter.add(currentThread);

        // 使线程不断的轮询获取锁
        for (; ; ) {
            // 判断等待队列的头节点是否为当前线程
            // 等待的线程依旧尝试不断的获取锁，若获取成功，跳出循环，执行后续逻辑
            if (waiter.peek() == currentThread && tryAcquire()) {
                // 移除获取到锁的线程（头节点）
                waiter.poll();
                break;
            }
            // 无法获取锁的线程进入阻塞，防止线程不断的循环浪费资源
            LockSupport.park();
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        // 当前线程非法持有锁
        if (currentThread != threadHolder) {
            throw new RuntimeException("获取锁的线程非当前线程！");
        }
        if (compareAndSwapState(1, 0)) {
            setThreadHolder(null);
            Thread headThread = waiters.peek();
            if (headThread != null) {
                // 当前线程释放锁，并唤醒等待队列的头节点
                LockSupport.unpark(headThread);
            }
        }
    }

    private boolean tryAcquire() {
        int state = getState();
        Thread currentThread = Thread.currentThread();
        if (state == 0) {
            // 若等待队列为空，或等待队列的头节点正好是当前线程，则让该线程获取锁
            if ((waiters.isEmpty() || currentThread == waiters.peek()) && compareAndSwapState(0, 1)) {
                setThreadHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    public boolean compareAndSwapState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, offset, expect, update);
    }

    private final static Unsafe unsafe = UnSafeInstance.reflect();
    private static long offset;

    static {
        try {
            // 获取对象属性的偏移量
            offset = unsafe.objectFieldOffset(AQSDemoLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
