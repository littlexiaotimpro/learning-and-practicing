package com.practice.thread.base;

import com.practice.thread.base.common.DataContext;

/**
 * 自定义线程
 */
public class OwnerThread extends Thread{

    private DataContext dataContext;

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if((i & 1) == 1) continue;
            System.out.println(getName());
            dataContext.change(i);
        }
    }
}
