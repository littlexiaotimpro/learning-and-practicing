package com.practice.thread.locks;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockObject {

    private final Map<String, LockCondition> lockConditionMap = new ConcurrentHashMap<>();

    private volatile long[] arr = new long[]{1, 2, 3, 4, 5, 6, 7, 8};

    public void lockMethod(String key) {
        synchronized (lockConditionMap){
            LockCondition lockCondition;
            if (lockConditionMap.containsKey(key)) {
                System.out.println("GET");
                lockCondition = lockConditionMap.get(key);
            } else {
                System.out.println("PUT");
                lockCondition = lockConditionMap.computeIfAbsent(key, k -> new LockCondition());
            }
            lockCondition.lock.lock();
            long id = Thread.currentThread().getId();
            System.out.println(id + " ********* KEY: " + key);
            System.out.println("Previous: " + Arrays.toString(arr));
            try {
                for (int i = 0; i < arr.length; i++) {
                    if ((i & 1) == 0) {
                        arr[i] = id;
                    }
                }
                System.out.println("After: " + Arrays.toString(arr));
                lockCondition.condition.signalAll();
            } finally {
                System.out.println("UNLOCK");
                lockCondition.lock.unlock();
            }
        }
    }


    private static class LockCondition {
        private final Lock lock;
        private final Condition condition;

        public LockCondition() {
            lock = new ReentrantLock();
            condition = lock.newCondition();
        }
    }
}
