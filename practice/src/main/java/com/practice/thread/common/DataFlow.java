package com.practice.thread.common;

import java.util.ArrayList;
import java.util.List;

public class DataFlow {

    // 测试多线程条件下的数据同步
    public List<Integer> SHARED_DATA = new ArrayList<>();
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
