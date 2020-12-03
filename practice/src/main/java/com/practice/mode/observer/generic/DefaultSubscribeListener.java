package com.practice.mode.observer.generic;

public class DefaultSubscribeListener implements SubscribeListener<DefaultSubscribeEvent> {

    @Override
    public void handleEvent(DefaultSubscribeEvent event) {
        System.out.println(this.getClass().getSimpleName() + " -> " + event.toString());
    }
}
