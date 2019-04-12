package com.degloba.domain.event;

import com.degloba.event.api.AbstractEvent;
import com.degloba.utils.Assert;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Event associat a una entitat de domini
 */
public abstract class DomainEvent extends AbstractEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id = UUID.randomUUID().toString();

    private Date occurredOn = new Date();

    private int version = 1;

    public DomainEvent() {
        this(new Date(), 1);
    }

    /**
     *
     * @param occurredOn Time of occurrence
     */
    public DomainEvent(Date occurredOn) {
        this(occurredOn, 1);
    }

    /**
     *
     * @param occurredOn Time of occurrence
     * @param version Version
     */
    public DomainEvent(Date occurredOn, int version) {
        Assert.notNull(occurredOn);
        this.occurredOn = new Date(occurredOn.getTime());
        this.version = version;
    }

    /**
     * Get event ID
     * @return ID event
     */
    public String getId() {
        return id;
    }

    /**
     * For test only
     * @param id To set the ID
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * Time to get the event
     * @return Event time
     */
    public Date getOccurredOn() {
        return occurredOn;
    }

    /**
     * Obtain version
     * @return Version of events
     */
    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DomainEvent)) {
            return false;
        }
        DomainEvent that = (DomainEvent) other;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
