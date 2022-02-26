package com.practice.thread.aqs;

import com.practice.thread.aqs.lock.AQSDemoLock;

import java.util.concurrent.*;

/**
 * AQS 共享资源锁应用
 *
 * @date 2022/2/25
 */
public class AQSDemo {

    private static class IThread extends Thread {

        private final AQSDemoLock lock = new AQSDemoLock();
        private final Stock stock;

        public IThread(String name, Stock stock) {
            super(name);
            this.stock = stock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (stock.stock <= 0) {
                    System.out.printf("库存不足, 线程【%s】无法执行\n", this.getName());
                } else {
                    System.out.printf("线程【%s】, 获取库存【%d】, 执行后库存【%d】\n", this.getName(), stock.stock, --stock.stock);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Stock stock = new Stock();
        for (int i = 0; i < 30; i++) {
            String tn = "t" + i;
            IThread iThread = new IThread(tn, stock);
            iThread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程结束");
    }

}
