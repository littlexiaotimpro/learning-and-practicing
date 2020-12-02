package com.practice.mode.observer;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("程序启动...");

        DefaultSubscribe defaultSubscribe = new DefaultSubscribe();

        Observer observerA = new DefaultObserver("A");
        defaultSubscribe.addObserver(observerA);

        defaultSubscribe.publish("填充对象内容...");

        Observer observerB = new DefaultObserver("B");
        defaultSubscribe.addObserver(observerB);

        defaultSubscribe.publish("内容变更...1");

        defaultSubscribe.removeObserver(observerA);

        defaultSubscribe.publish("内容变更...1");

        System.out.println("程序结束...");
    }
}
