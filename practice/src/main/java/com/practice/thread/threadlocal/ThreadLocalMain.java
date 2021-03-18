package com.practice.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ThreadLocalMain {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            Thread.currentThread().setName("thread-1");
            Context.setInteger(1);

            log.info("{}", Context.getInteger());
        });
        Thread thread2 = new Thread(() -> {
            Thread.currentThread().setName("thread-2");
            Context.setInteger(2);

            List<Integer> integer = Context.getInteger();
            Context.removeInteger();
            log.info("{}:{}", integer, Context.getInteger());
        });

        thread1.start();
        thread2.start();
        log.info("{}", Context.getInteger());
    }

}
