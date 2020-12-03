package com.practice.mode.observer.generic;

import java.util.EventListener;

@FunctionalInterface
public interface SubscribeListener<E extends AbstractSubscribeEvent> extends EventListener {

    void handleEvent(E event);

}
