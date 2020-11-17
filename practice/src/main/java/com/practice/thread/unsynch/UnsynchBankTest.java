package com.practice.thread.unsynch;

/**
 * @author XiaoSi
 * @className UnsynchBankTest
 * @description 测试线程同步类
 * @date 2020/11/17
 */
public class UnsynchBankTest {

    public static final int ACCOUNTS_NUM = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    /**
     * 如何选择 Lock 和 Condition 还是加同步关键字？
     */
    public static void main(String[] args) {
        // 1.加锁
        // Bank bank = new Bank(ACCOUNTS_NUM, INITIAL_BALANCE);
        // 2.加 synchronized
        SyncBank bank = new SyncBank(ACCOUNTS_NUM, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNTS_NUM; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep(DELAY);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

}
