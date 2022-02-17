package com.practice.thread.locks.eg7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * JUC下的LockSupport测试
 */
public class LockSupportMain03 {

    private final static Logger log = LoggerFactory.getLogger(LockSupportMain03.class);

    public static void main(String[] args) throws InterruptedException {
        IThread t1 = new IThread("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("主线程结束");
    }

    private static class IThread extends Thread {

        public IThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                log.info("进入线程【{}】并调用LockSupport.park()", this.getName());
                LockSupport.parkUntil(System.currentTimeMillis() + 3000);
                log.info("线程【{}】达到Deadline时限，继续执行并结束", this.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
