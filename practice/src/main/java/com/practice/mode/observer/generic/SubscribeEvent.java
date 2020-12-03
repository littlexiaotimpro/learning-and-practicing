package com.practice.mode.observer.generic;

public class SubscribeEvent extends AbstractSubscribeEvent {

    private String code;

    public SubscribeEvent(Object source) {
        super(source);
    }

    public SubscribeEvent(Object source, String code) {
        super(source);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{code=" + code + "}";
    }
}
