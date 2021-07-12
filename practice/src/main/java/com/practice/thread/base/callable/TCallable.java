package com.practice.thread.base.callable;

import java.util.concurrent.Callable;

public class TCallable implements Callable<String> {

    private final String tag;

    public TCallable(String tag) {
        this.tag = tag;
    }

    @Override
    public String call() throws Exception {
        // 等待3s
        Thread.sleep(3000);
        return tag + " -> " + Thread.currentThread().toString();
    }
}
