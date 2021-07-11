package com.practice.thread.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
        this.bankLock = new ReentrantLock();
        this.sufficientFunds = bankLock.newCondition();
    }

    /**
     * 当前多线程执行当前逻辑时，可能存在线程执行一半后被其他线程抢占资源后进入等待
     * 其它线程正常结束后，当前线程重新激活执行，修改了其它线程修改过的值导致结果出现误差
     * <p>
     * 解决方式一：加锁{@link ReentrantLock}，默认非公平锁（NonfairSync）
     * 通过加锁的方式锁定当前对象的方法逻辑操作
     * <p>
     * 锁和条件
     * * 锁用来保护代码片段，任何时刻只能有一个线程执行被保护的代码
     * * 锁可以用来管理试图进入被保护代码段的线程
     * * 锁可以拥有一个或多个相关的条件对象
     * * 每个条件对象管理那些已经进入被保护的代码段但不能运行的线程
     * </p>
     */
    public void transfer(int from, int to, double amount) {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                /*
                 * 当最后一个活动线程在解除其它线程的阻塞状态之前调用await方法进入阻塞后，
                 * 程序进入死锁（deadlock）状态，因为不存在能够解除阻塞状态的活动线程了
                 *
                 * 等待获取锁的线程和调用 await 方法的线程本质上不同：
                 * 调用 await 的线程，会进入该条件的等待集，
                 * 当锁可用时，不会立即解除阻塞，直到另一个线程调用同一个条件上的signalAll方法为止
                 */
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread().getName());
            System.out.printf(" Account of [from:%d] balance is [%.2f]，[to:%d] balance is [%.2f]", from, accounts[from], to, accounts[to]);
            accounts[from] -= amount;
            System.out.printf("%10.2f from [%d] to [%d]", amount, from, to);
            accounts[to] += amount;
            System.out.printf(", Total Balance: %10.2f\n", getTotalBalance());
            /*
             * 为了避免死锁，在每个活动线程正常执行转账后，就应该通知阻塞的线程，让等待的线程有机会检查余额
             * signalAll/signal 不会立即激活一个等待线程，仅仅只是解除（同条件上的）线程的阻塞状态
             * 以便当前线程结束后，能够有机会获取资源实现对对象的访问
             */
            sufficientFunds.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bankLock.unlock();
        }
    }

    private double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0d;
            for (double amount : accounts) {
                sum += amount;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}