package com.practice.thread;

import com.practice.thread.common.DataContext;

public class ContextMain {

    private static final DataContext dataContext = new DataContext();

    public static void main(String[] args) throws InterruptedException {
        // 定义线程
        OwnerThread ownerThread = new OwnerThread();
        ownerThread.setDataContext(dataContext);
        OwnerRunnable ownerRunnable = new OwnerRunnable();
        ownerRunnable.setDataContext(dataContext);
        Thread thread = new Thread(ownerRunnable);

        // 开启线程
        dataContext.change(0);
        Thread.sleep(3000);
        ownerThread.start();
        thread.start();

        System.out.println(dataContext.getDataFlow().SHARED_DATA);
    }

}
