package com.practice.thread.locks.eg1;

/**
 * 启动类
 */
public class Sale {

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

/**
 * 资源类
 */
class Source {

    private int number = 20;

    public synchronized void subtract() {
        if (number > 0) {
            Thread thread = Thread.currentThread();
            number--;
            System.out.printf("线程: %s, 剩余: %d%n", thread.getName(), number);
        }
    }
}