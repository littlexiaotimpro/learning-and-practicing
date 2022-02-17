package com.practice.thread.locks.eg7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * JUC下的LockSupport测试
 */
public class LockSupportMain02 {

    private final static Logger log = LoggerFactory.getLogger(LockSupportMain02.class);

    public static void main(String[] args) throws InterruptedException {
        IThread t1 = new IThread("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        // park方法可以响应线程中断
        t1.interrupt();
        log.info("线程【{}】调用interrupt，执行结束", t1.getName());
    }

    private static class IThread extends Thread {

        public IThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                /*
                 * LockSupport.park方法让线程等待之后，
                 * 唤醒方式有2种：
                 * - 调用LockSupport.unpark方法
                 * - 调用等待线程的interrupt方法，给等待的线程发送中断信号，可以唤醒线程
                 */
                log.info("进入线程【{}】并调用LockSupport.park()", this.getName());
                log.info("进入线程【{}】中断属性值【{}】", this.getName(), this.isInterrupted());
                LockSupport.park();
                log.info("进入线程【{}】中断属性值【{}】", this.getName(), this.isInterrupted());
                log.info("线程【{}】继续执行，并结束", this.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
