package com.practice.thread.locks.eg8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 */
public class SemaphoreMain05 {
    private final static Logger log = LoggerFactory.getLogger(SemaphoreMain05.class);
    // 创建公平信号量
    private static final Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            IThread t = new IThread("t" + i);
            t.start();
        }
        TimeUnit.SECONDS.sleep(3);
        log.info("主线程结束");
    }

    private static class IThread extends Thread {
        public IThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                // 申请许可
                semaphore.acquire();
                log.info("线程【{}】申请许可，剩余可用许可【{}】", this.getName(), semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放许可
                semaphore.release();
                log.info("线程【{}】释放许可，剩余可用许可【{}】", this.getName(), semaphore.availablePermits());
            }
        }
    }

}
