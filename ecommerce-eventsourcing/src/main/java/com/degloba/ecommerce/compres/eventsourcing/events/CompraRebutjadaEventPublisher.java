package com.degloba.ecommerce.compres.eventsourcing.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.degloba.ecommerce.compres.eventsourcing.impl.spring.events.CompraRebutjadaEvent;


/**
 * @category publicador d'events de tipus {@link CompraRebutjadaEvent}.<br>
 * 
 * Implementat amb Spring/natiu
 * 
 * @author degloba
 *
 */
@Component
public class CompraRebutjadaEventPublisher {
	
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
 
    public void publicaEvent(final String message) {
        System.out.println("Publishing custom event. ");
        CompraRebutjadaEvent compraRebutjadaEvent = new CompraRebutjadaEvent(this, message,true);
        applicationEventPublisher.publishEvent(compraRebutjadaEvent);
    }
}
