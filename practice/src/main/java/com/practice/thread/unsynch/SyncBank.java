package com.practice.thread.unsynch;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className Bank
 * @description 测试线程同步的银行实体类
 * @date 2020/11/17
 */
public class SyncBank {

    private final double[] accounts;

    public SyncBank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
    }

    /**
     * 当前多线程执行当前逻辑时，可能存在线程执行一半后被其他线程抢占资源后进入等待
     * 其它线程正常结束后，当前线程重新激活执行，修改了其它线程修改过的值导致结果出现误差
     * <p>
     * 解决方式二：synchronized
     * 通过使用使用此关键字获取对象的内部锁实现
     * <p>
     *     内部锁和条件的局限性
     *     * 不能中断一个正在视图获得锁的线程
     *     * 视图获得锁时不能设定超时
     *     * 每个锁仅有单一的条件，这可能是不够的
     * </p>
     */
    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        while (accounts[from] < amount) {
            /*
             * 内部对象锁只有一个相关条件
             * 调用对象的 wait 方法，将当前线程添加到等待集中
             */
            wait();
        }
        System.out.print(Thread.currentThread());
        System.out.printf(" Account of [from:%d] balance is [%.2f]，[to:%d] balance is [%.2f]", from, accounts[from], to, accounts[to]);
        accounts[from] -= amount;
        System.out.printf("%10.2f from [%d] to [%d]", amount, from, to);
        accounts[to] += amount;
        System.out.printf(", Total Balance: %10.2f\n", getTotalBalance());
        /*
         * 调用 notifyAll/notify 方法解除等待线程的阻塞状态
         * 这些方法是Object类的final方法
         */
        notifyAll();
    }

    private synchronized double getTotalBalance() {
        double sum = 0d;
        for (double amount : accounts) {
            sum += amount;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}