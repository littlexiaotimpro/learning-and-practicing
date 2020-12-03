package com.practice.mode.observer.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractEventPublisher {

    // 监听器集合，初始化
    private final Set<SubscribeListener<?>> listeners;

    {
        listeners = new LinkedHashSet<>();
        listeners.addAll(Arrays.asList(new DefaultSubscribeListener(), new SimpleSubscribeListener()));
    }

    /**
     * 依据事件类型获取对应的监听器
     */
    protected Collection<SubscribeListener<?>> getEventListener(AbstractSubscribeEvent event) {
        // 事件类型
        Object source = event.getSource();
        Class<?> sourceType = source != null ? source.getClass() : null;

        // 获取事件对应的监听器
        Set<SubscribeListener<?>> eventListener = new LinkedHashSet<>();
        if (sourceType == null) {
            return eventListener;
        }
        for (SubscribeListener<?> listener : this.listeners) {
            Type[] genericInterfaces = listener.getClass().getGenericInterfaces();
            for (Type type : genericInterfaces) {
                ParameterizedType type1 = (ParameterizedType) type;
                Type[] actualTypeArguments = type1.getActualTypeArguments();
                if (actualTypeArguments != null) {
                    for (Type actualTypeArgument : actualTypeArguments) {
                        if (actualTypeArgument.getTypeName().equals(event.getClass().getName())) {
                            eventListener.add(listener);
                        }
                    }
                }
            }
        }
        return eventListener;
    }

    protected void publish(AbstractSubscribeEvent event) {
        for (SubscribeListener<?> subscribeListener : getEventListener(event)) {
            invokeListener(subscribeListener, event);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void invokeListener(SubscribeListener listener, AbstractSubscribeEvent event) {
        listener.handleEvent(event);
    }

}
