package com.degloba.event.bus;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventBus;
import com.degloba.event.api.IEventListener;
import com.degloba.event.api.IEventStore;
import com.degloba.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Within a simple process event bus
 */
public final class SimpleEventBus<T extends IEvent> implements IEventBus<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventBus.class);

    private IEventStore eventStore;

    private List<IEventListener<T>> listeners = new ArrayList<IEventListener<T>>();

    public SimpleEventBus(IEventStore eventStore) {
        this.eventStore = eventStore;
    }

    public SimpleEventBus(IEventStore eventStore, List<IEventListener<T>> listeners) {
        Assert.notNull(eventStore, "Event Store is null.");
        this.eventStore = eventStore;
        Assert.notEmpty(listeners, "listeners must not be null or empty.");
        this.listeners = Collections.unmodifiableList(listeners);
    }

    List<IEventListener<T>> getListeners() {
        return listeners;
    }

    @Override
    public void register(IEventListener<T> listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    @Override
    public void unregister(IEventListener<T> listeners) {
        this.listeners.removeAll(Arrays.asList(listeners));
    }

    @Override
    public void post(T event) {
        LOGGER.info("Post a event " + event + " to event bus");
        eventStore.store(event);
        for (IEventListener<T> listener : listeners) {
            listener.onEvent(event);
        }
    }
}
