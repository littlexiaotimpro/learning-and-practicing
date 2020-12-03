package com.practice.mode.observer.generic;

import java.util.EventObject;

public abstract class AbstractSubscribeEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AbstractSubscribeEvent(Object source) {
        super(source);
    }
}