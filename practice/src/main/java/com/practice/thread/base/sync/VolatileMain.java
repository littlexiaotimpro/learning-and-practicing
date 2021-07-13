package com.practice.thread.base.sync;

public class VolatileMain {

    // 保证变量在线程之间的可见性
    private volatile static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA(), "A").start();
        Thread.sleep(1000);
        new Thread(new ThreadB(), "B").start();
    }

    private static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (value < 5) {
                if ((value & 1) == 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + value);
                    value = value + 1;
                }
            }
        }
    }

    private static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (value < 5) {
                if ((value & 1) == 1) {
                    System.out.println(Thread.currentThread().getName() + " : " + value);
                    value = value + 1;
                }
            }
        }
    }

}
