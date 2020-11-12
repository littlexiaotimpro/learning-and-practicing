package com.practice.thread.base.common;

import java.util.ArrayList;
import java.util.List;

public class DataFlow {

    // 测试多线程条件下的数据同步
    public final List<Integer> SHARED_DATA = new ArrayList<>();

    public void changeData(Integer i){
        SHARED_DATA.add(i);
    }
}
