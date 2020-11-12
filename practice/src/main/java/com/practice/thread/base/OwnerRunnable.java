package com.practice.thread.base;

import com.practice.thread.base.common.DataContext;

public class OwnerRunnable implements Runnable {

    private DataContext dataContext;

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void run() {
        dataContext.change(1);
        System.out.println("自定义的实现Runnable接口的线程的执行方法");
    }
}
