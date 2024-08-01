package com.practice.thread.locks.completableFuture;

import java.util.concurrent.*;

/**
 * CompletableFuture
 */
public class CompletableFutureDemo {

    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6,
            10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "线程" + Math.random());
        }
    });

    public static void main(String[] args) {
        CompletableFuture<Void> root = CompletableFuture.supplyAsync(() -> {
            System.out.println("1--" + Thread.currentThread().getName());
            return "1";
        }, executor).thenApply(x -> {
            System.out.println("2--" + Thread.currentThread().getName());
            return x + "2";
        }).thenApplyAsync(x -> {
            System.out.println("3--" + Thread.currentThread().getName());
            return x + "3";
        }, executor).thenAccept(x -> {
            System.out.println("4--" + Thread.currentThread().getName());
        }).thenAcceptAsync(x -> {
            System.out.println("5--" + Thread.currentThread().getName());
        }, executor).thenRun(() -> {
            System.out.println("6--" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            System.out.println("7--" + Thread.currentThread().getName());
        }, executor);

        try {
            root.get();
        } catch (Exception e) {
        }

        executor.shutdownNow();
    }

}
