package com.practice.io.bio.demo07.constant;

public enum Flag {
    LOGIN(0),
    TO_ALL(1),
    TO_SOMEONE(2),
    TO_DEST_ONE(3);

    private final int flag;

    Flag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public static Flag convert(int flag) {
        Flag[] flags = Flag.values();
        for (Flag flag1 : flags) {
            if (flag == flag1.flag) {
                return flag1;
            }
        }
        throw new IllegalArgumentException("非法操作！");
    }
}
