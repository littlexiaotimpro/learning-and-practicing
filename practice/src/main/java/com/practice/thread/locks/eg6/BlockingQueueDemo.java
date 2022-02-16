package com.practice.thread.locks.eg6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo<E> {

    private final static Logger log = LoggerFactory.getLogger(BlockingQueueDemo.class);

    int size;

    ReentrantLock lock = new ReentrantLock();

    LinkedList<E> list = new LinkedList<>();

    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public BlockingQueueDemo(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            log.info("线程:【{}】", Thread.currentThread().getName());
            while (list.size() == size) {
                notFull.await();
            }
            list.add(e);
            log.info("入队: {}", e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            log.info("线程:【{}】", Thread.currentThread().getName());
            while (list.size() == 0) {
                notEmpty.await();
            }
            e = list.removeFirst();
            log.info("出队: {}", e);
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(2);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(() -> {
                try {
                    queue.enqueue(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "enqueue" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Integer dequeue = queue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "dequeue" + i).start();
        }
    }

}
