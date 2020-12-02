package com.practice.mode.observer.jdk;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("程序启动...");

        SimpleObservable simpleObservable = new SimpleObservable();
        simpleObservable.setContext("填充对象内容...");

        SimpleObserver simpleObserverA = new SimpleObserver("A");
        simpleObservable.addObserver(simpleObserverA);
        SimpleObserver simpleObserverB = new SimpleObserver("B");
        simpleObservable.addObserver(simpleObserverB);

        simpleObservable.publish("内容变更...1");

        simpleObservable.deleteObserver(simpleObserverA);

        simpleObservable.publish("内容变更...2");

        System.out.println("程序结束...");
    }
}
