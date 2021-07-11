package com.practice.thread.base;

import com.practice.thread.base.common.DataContext;

public class OwnerRunnable implements Runnable {

    private DataContext dataContext;

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if ((i & 1) == 0) continue;
            System.out.println(Thread.currentThread().getName());
            dataContext.change(i);
        }
    }
}
