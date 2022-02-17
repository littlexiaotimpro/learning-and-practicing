package com.practice.thread.locks.eg7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * JUC下的LockSupport测试
 */
public class LockSupportMain {

    private final static Logger log = LoggerFactory.getLogger(LockSupportMain.class);

    public static void main(String[] args) throws InterruptedException {
        IThread t1 = new IThread("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t1);
        log.info("线程【{}】调用LockSupport.unpark()，执行结束", t1.getName());
    }

    private static class IThread extends Thread {

        public IThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                /*
                 * 唤醒方法在等待方法之前执行，线程也能够被唤醒，这点是另外2中方法无法做到的。
                 * Object和Condition中的唤醒必须在等待之后调用，线程才能被唤醒。
                 * 而LockSupport中，唤醒的方法不管是在等待之前还是在等待之后调用，线程都能够被唤醒
                 */
                TimeUnit.SECONDS.sleep(5);
                log.info("进入线程【{}】并调用LockSupport.park()", this.getName());
                LockSupport.park();
                log.info("线程【{}】继续执行，并结束", this.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
