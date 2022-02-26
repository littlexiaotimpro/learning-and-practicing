package com.practice.thread.locks.eg2;

import java.util.concurrent.locks.ReentrantLock;

public class LockSale {
    public static void main(String[] args) {
        Source source = new Source();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 8; j++) {
                    source.subtract();
                }
            }, "TD-" + i);
            thread.start();
        }
    }
}

class Source {

    private int number = 20;

    private final ReentrantLock lock = new ReentrantLock();

    public void subtract() {
        lock.lock();
        try {
            if (number > 0) {
                Thread thread = Thread.currentThread();
                number--;
                System.out.printf("线程: %s, 剩余: %d%n", thread.getName(), number);
            }
        } finally {
            lock.unlock();
        }
    }

}