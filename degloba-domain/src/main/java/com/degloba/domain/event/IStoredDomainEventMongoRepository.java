package com.degloba.domain.event;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.domain.annotations.DomainRepository;



@DomainRepository
public interface IStoredDomainEventMongoRepository extends MongoRepository<StoredDomainEventMongoDb, String> {


	  /**
     * GetA collection of historical events occurred specified time range, 
     * the time range includes occurredFrom, does not contain occurredTo.
     *
     * @param occurredFrom Event time limit
     * @param occurredTo   The upper limit time of the incident
     * @return Collection event occurred a specified time frame, according to time of occurrence in ascending order.
     */
    public List<StoredDomainEventMongoDb> findStoredEventsBetween(Date occurredFrom, Date occurredTo);

    /**
     * Get A collection of historical events that occur after the specified time and the.
     *
     * @param occurredFrom Event time limit
     * @return Its collection of events that occur after the specified time, 
     * according to time of occurrence in ascending order.
     */
    public List<StoredDomainEventMongoDb> findStoredEventsSince(Date occurredFrom);

    /**
     * Insert a new field events to the event store
     *
     * @param domainEvent A field event
     * @return Field events stored on behalf of the event
     */
    public StoredDomainEventMongoDb append(DomainEvent domainEvent);

    /**
     * Close event storage
     */
    public void close();

    /**
     * Statistics of stored events
     *
     * @return Number of stored events
     */
//    public long countStoredEvents();
}
