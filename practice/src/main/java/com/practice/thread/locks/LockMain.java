package com.practice.thread.locks;

import java.util.Arrays;

public class LockMain {

    public static void main(String[] args) {
        LockObject lockObject = new LockObject();

        for (int i = 0; i < 10; i++) {
            String key;
            if ((i & 1) == 0) {
                key = "0_key";
            } else {
                key = "1_key";
            }
            Runnable run = () -> {
                try {
                    while (true) {
                        long[] longs = lockObject.lockMethod(key);
                        System.out.println("After: " + Arrays.toString(longs));
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(run);
            thread.start();
        }
    }
}
