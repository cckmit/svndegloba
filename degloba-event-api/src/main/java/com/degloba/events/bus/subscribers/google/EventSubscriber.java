package com.degloba.events.bus.subscribers.google;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;

/**
 * @category Subscriptor d'events amb un bus d'events implementat amb Google
 * 
 * @author degloba
 */
public abstract class EventSubscriber<T> {

    protected List<T> events = new ArrayList<T>();

    public List<T> getHandledEvents() {
        return events;
    }


    /**
     * @category registra un {@link EventSubscriber} d'un tipus de classe en el {@link EventBus}
     * @param clazz
     * @param eventBus
     * @return
     */
    public static <E extends EventSubscriber<?>> E factory(Class<E> clazz, EventBus eventBus) {
        E subscriber = null;
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            subscriber = (E) constructor.newInstance(new Object[]{});
            eventBus.register(subscriber);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return subscriber;
    }
}