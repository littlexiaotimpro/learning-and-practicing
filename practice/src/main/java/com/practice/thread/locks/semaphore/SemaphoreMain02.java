package com.practice.thread.locks.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 */
public class SemaphoreMain02 {
    private final static Logger log = LoggerFactory.getLogger(SemaphoreMain02.class);
    // 创建不公平信号量
    private static final Semaphore semaphore = new Semaphore(3, false);

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
                boolean b;
                // tryAcquire申请许可，无论是否申请成功都会立即返回，成功返回true
                if ((Integer.parseInt(this.getName().split("t")[1]) & 1) == 0) {
                    // 偶数需要双数的许可
                    b = semaphore.tryAcquire(2);
                } else {
                    b = semaphore.tryAcquire();
                }
                if (b) {
                    log.info("线程【{}】申请许可成功", this.getName());
                } else {
                    log.info("线程【{}】申请许可失败", this.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.info("线程【{}】释放许可", this.getName());
                // 释放许可
                semaphore.release();
            }
        }
    }

}
