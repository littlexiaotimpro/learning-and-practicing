package com.practice.mode.observer.generic;

public class DefaultSubscribeEvent extends AbstractSubscribeEvent {

    private String code;
    private String context;

    public DefaultSubscribeEvent(Object source) {
        super(source);
    }

    public DefaultSubscribeEvent(Object source, String code, String context) {
        super(source);
        this.code = code;
        this.context = context;
    }

    public String getCode() {
        return code;
    }

    public String getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "{code=" + code + ", context=" + context + "}";
    }
}
