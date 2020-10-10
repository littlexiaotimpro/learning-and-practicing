package com.practice.thread;

/**
 * 自定义线程
 */
public class OwnerThread extends Thread{

    @Override
    public void run() {
        System.out.println("自定义的继承线程类的执行方法");
    }
}
