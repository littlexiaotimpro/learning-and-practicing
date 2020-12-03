package com.practice.mode.observer.generic;

public class DefaultEventPublisher extends AbstractEventPublisher {

    public void publishDefault(String code, String context) {
        DefaultSubscribeEvent defaultEvent =
                new DefaultSubscribeEvent(this, code, context);
        publish(defaultEvent);
    }

    public void publishSimple(String code) {
        SubscribeEvent subscribeEvent =
                new SubscribeEvent(this, code);
        publish(subscribeEvent);
    }

}
