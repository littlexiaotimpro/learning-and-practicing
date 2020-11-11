package com.practice.start.common;

public class DataBean {

    private int count = 10;

    public int getCount() {
        return count;
    }

    public void subtract() {
        synchronized (this){
            this.count--;
        }
        if (this.count < 0) {
            throw new RuntimeException("The count is less then zero!");
        }
    }

}
