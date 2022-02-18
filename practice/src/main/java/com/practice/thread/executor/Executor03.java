package com.practice.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池应用测试
 * <p>
 * target: 优先队列的使用
 *
 * @date 2022/2/18
 */
public class Executor03 {

    private final static Logger log = LoggerFactory.getLogger(Executor03.class);

    public static void main(String[] args) throws InterruptedException {
        // 仅提供一个线程执行任务，查看线程是否如预期的按任务优先级进行降序处理
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                30L, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            IRunnable iRunnable = new IRunnable(i, "任务" + i);
            executor.execute(iRunnable);
        }
        for (int i = 20; i >= 10; i--) {
            IRunnable iRunnable = new IRunnable(i, "任务" + i);
            executor.execute(iRunnable);
        }
        executor.shutdown();
        log.info("关闭线程池");
    }

    private static class IRunnable implements Runnable, Comparable<IRunnable> {

        private final static Logger log = LoggerFactory.getLogger(IRunnable.class);

        private final int index;
        private final String name;

        public IRunnable(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            log.info("线程【{}】处理【{}】完毕", Thread.currentThread().getName(), this.toString());
        }

        /**
         * 优先队列通过调用此方法对任务进行优先级排序
         *
         * @param o 已入队的任务
         * @return 优先级：[1:this>o, 0:this=o, -1:this<o]
         */
        @Override
        public int compareTo(IRunnable o) {
            return Integer.compare(o.index, index);
        }

        @Override
        public String toString() {
            return String.format("{index: %d, name: %s}", index, name);
        }
    }

}
