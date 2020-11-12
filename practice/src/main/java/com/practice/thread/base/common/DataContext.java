package com.practice.thread.base.common;

public class DataContext {

    private final DataFlow dataFlow = new DataFlow();

    public void change(Integer i){
        synchronized (dataFlow.SHARED_DATA){
            dataFlow.changeData(i);
        }
    }

    public DataFlow getDataFlow() {
        return dataFlow;
    }
}
