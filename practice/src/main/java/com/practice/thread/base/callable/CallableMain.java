package com.practice.thread.base.callable;

import java.util.concurrent.*;

public class CallableMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // 获取实现了 Callable 接口的线程任务的返回值
            for (int i = 0; i < 3; i++) {
                TCallable tCallable = new TCallable("T" + i);
                Future<String> result = executorService.submit(tCallable);
                System.out.println(result.get());
            }

            // FutureTask JDK提供的实现了Runnable和Future接口的实现类型
            TCallable tCallable = new TCallable("TT");
            FutureTask<String> task = new FutureTask<>(tCallable);
            executorService.submit(task);
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
