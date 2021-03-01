package com.practice.reflection;

public class Demo extends SuperDemo {
    private int target;
    private String content;

    public Demo() {
    }

    public Demo(int target, String content) {
        this.target = target;
        this.content = content;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{target = " + target + ", content = " + content + "}";
    }
}
