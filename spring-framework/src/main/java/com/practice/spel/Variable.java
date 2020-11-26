package com.practice.spel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoSi
 * @className Variable
 * @description 参数
 * @date 2020/11/26
 */
public class Variable {
    private String prop;
    public boolean flag;
    public List<Boolean> booleanList = new ArrayList<>();
    public List<String> list;

    public Variable() {
    }

    public Variable(String prop) {
        this.prop = prop;
    }

    public Variable(boolean flag) {
        this.flag = flag;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}
