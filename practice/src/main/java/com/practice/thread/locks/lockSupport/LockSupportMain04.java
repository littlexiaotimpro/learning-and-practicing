package com.practice.thread.locks.lockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * JUC下的LockSupport测试
 */
public class LockSupportMain04 {

    private final static Logger log = LoggerFactory.getLogger(LockSupportMain04.class);

    public static void main(String[] args) throws InterruptedException {
        IThread t1 = new IThread("t1");
        t1.start();
        JThread t2 = new JThread("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t1);
        log.info("线程【{}】调用LockSupport.unpark，执行结束", t1.getName());
    }

    private static class IThread extends Thread {

        public IThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                log.info("进入线程【{}】并调用LockSupport.park()", this.getName());
                LockSupport.park();
                log.info("线程【{}】继续执行，并结束", this.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class JThread extends Thread {

        public JThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                log.info("进入线程【{}】并调用LockSupport.park()", this.getName());
                LockSupport.parkUntil(new Block(), System.currentTimeMillis() + 3000);
                log.info("线程【{}】达到Deadline时间，继续执行并结束", this.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Block {
    }
}
