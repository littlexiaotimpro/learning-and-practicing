package com.practice.mode.observer.generic;

public class SimpleSubscribeListener implements SubscribeListener<SubscribeEvent> {

    @Override
    public void handleEvent(SubscribeEvent event) {
        System.out.println(this.getClass().getSimpleName() + " -> " + event.toString());
    }
}
