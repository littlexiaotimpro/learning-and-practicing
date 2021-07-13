package com.practice.thread.base.sync;

/**
 * @see Object#wait()
 * @see Object#notify()
 * @see Object#notifyAll()
 */
public class WaitMain {

    /**
     * 要想实现线程之间的通信，多个线程的锁对象必须是同一个
     */
    private void method() {
        Thread threadA = new Thread(() -> {
            synchronized (this) {
                Thread t = Thread.currentThread();
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(t.getName() + " -> 执行：" + i + "，状态：" + t.getState());
                        this.notify();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.notify();
            }
        }, "thread-a");

        Thread threadB = new Thread(() -> {
            synchronized (this) {
                Thread t = Thread.currentThread();
                for (int i = 0; i < 10; i++) {
                    try {
                        // 打印A线程状态：thread-b：[thread-a:WAITING]
                        System.out.println(t.getName() + "：[" + threadA.getName() + ":" + threadA.getState() + "]");
                        System.out.println(t.getName() + "，执行：" + i + "，状态：" + t.getState());
                        this.notify();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.notify();
            }
        }, "thread-b");

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        WaitMain waitMain = new WaitMain();
        waitMain.method();
    }
}
