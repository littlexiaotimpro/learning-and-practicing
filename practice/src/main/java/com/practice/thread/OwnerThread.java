package com.practice.thread;

import com.practice.thread.common.DataContext;

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
        dataContext.change(2);
        System.out.println("自定义的继承线程类的执行方法");
    }
}
