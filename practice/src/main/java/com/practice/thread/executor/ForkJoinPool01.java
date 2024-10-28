package com.practice.thread.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool测试
 */
public class ForkJoinPool01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        ForkTask forkTask = new ForkTask(50);

        ForkJoinTask<Integer> submit = forkJoinPool.submit(forkTask);

        while (!submit.isDone()) {
        }
        System.out.println(submit.get());
    }

    public static class ForkTask extends RecursiveTask<Integer> {

        private final int num;

        public ForkTask(int num) {
            this.num = num;
        }

        @Override
        protected Integer compute() {
            String tName = Thread.currentThread().getName();
            if (num > 20) {
                System.out.println(tName + "--任务拆分: " + num);

                ForkTask forkTask = new ForkTask(10);
                ForkTask forkTask1 = new ForkTask(num - 10);

                forkTask.fork();
                forkTask1.fork();

                forkTask.join();
                forkTask1.join();

                Integer rawResult = forkTask.getRawResult();
                Integer rawResult1 = forkTask1.getRawResult();
                return rawResult + rawResult1;
            }

            System.out.println(tName + "--任务结果返回: " + num);
            return num;
        }
    }
}
