package com.practice.mode.observer.self;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("程序启动...");

        DefaultSubscribe defaultSubscribe = new DefaultSubscribe();

        Observer observerA = new DefaultObserver("A");
        defaultSubscribe.addObserver(observerA);

        System.out.println("********* publish.1 ********");
        defaultSubscribe.publish("填充对象内容...");

        Observer observerB = new DefaultObserver("B");
        defaultSubscribe.addObserver(observerB);

        System.out.println("********* publish.2 ********");
        defaultSubscribe.publish("内容变更...1");

        defaultSubscribe.removeObserver(observerA);

        // 内容和之前的一样，观察者标识内容未变更，不做响应
        System.out.println("********* publish.3 ********");
        defaultSubscribe.publish("内容变更...1");

        System.out.println("********* publish.4 ********");
        defaultSubscribe.publish("内容变更...2");

        System.out.println("程序结束...");
    }
}
