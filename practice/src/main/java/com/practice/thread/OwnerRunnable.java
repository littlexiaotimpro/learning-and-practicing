package com.practice.thread;

public class OwnerRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("自定义的实现Runnable接口的线程的执行方法");
    }
}
