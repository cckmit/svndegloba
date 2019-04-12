package com.degloba.event.api;

/**
 * Interfície : Listener d'events de tipus {@link IEvent}
 */
public interface IEventListener<T extends IEvent> {

    void onEvent(T event);

}
