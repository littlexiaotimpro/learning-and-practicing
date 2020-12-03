package com.practice.mode.observer.generic;

public class TestListener {

    public static void main(String[] args) {
        System.out.println("开始-------------------");
        DefaultEventPublisher defaultEventPublisher = new DefaultEventPublisher();
        defaultEventPublisher.publishDefault("default-001", "default-context");
        System.out.println("1-------------------");
        defaultEventPublisher.publishSimple("simple-001");
        System.out.println("2-------------------");
        defaultEventPublisher.publishSimple("simple-002");
        System.out.println("3-------------------");
        defaultEventPublisher.publishSimple("simple-003");
        System.out.println("4-------------------");
        defaultEventPublisher.publishDefault("default-002","default-changed");
        System.out.println("结束-------------------");


    }

}
