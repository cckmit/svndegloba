package com.degloba.event.persistence;

import com.degloba.event.api.IEvent;

/**
 * @category Magatzem d'events de domini
 * 
 *  * @author degloba
 */
public interface IStoredDomainEvent  extends IEventStore<IEvent> {

}