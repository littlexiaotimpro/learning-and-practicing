package com.practice.thread.locks.eg8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 */
public class SemaphoreMain04 {
    private final static Logger log = LoggerFactory.getLogger(SemaphoreMain04.class);
    // 创建不公平信号量，仅一个线程能够获取许可
    private static final Semaphore semaphore = new Semaphore(1, false);

    public static void main(String[] args) throws InterruptedException {
        IThread t1 = new IThread("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        IThread t2 = new IThread("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        IThread t3 = new IThread("t3");
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
        t3.interrupt();
        log.info("主线程结束");
    }

    private static class IThread extends Thread {
        public IThread(String name) {
            super(name);
        }

        // 成功申请许可标识
        private boolean acquired = false;

        @Override
        public void run() {
            try {
                // tryAcquire()申请许可
                semaphore.acquire();
                acquired = true;
                log.info("线程【{}】申请许可成功", this.getName());
                log.info("线程【{}】释放许可，可用许可数【{}】", this.getName(), semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 线程t1获取许可后，其他线程因为响应中断操作而释放许可，导致出现异常可用许可数目出现
                // 解决方法是只有获取了许可的线程可以进行许可释放
                if (acquired) {
                    semaphore.release();
                    log.info("线程【{}】释放许可，可用许可数【{}】", this.getName(), semaphore.availablePermits());
                }
            }
        }
    }

}
