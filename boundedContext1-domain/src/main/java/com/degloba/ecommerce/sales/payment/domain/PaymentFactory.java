package com.degloba.ecommerce.sales.payment.domain;

import java.util.UUID;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainFactory;

import com.degloba.organisation.canonicalmodel.events.ClientPaidEvent;


// Domain
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;

// Event
import com.degloba.event.domain.IDomainEventPublisher;

// Google app engine
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 
 * @author degloba
 *
 */
@DomainFactory
public class PaymentFactory {
	
	@Inject
	private IDomainEventPublisher<?> publisher;

	public Payment createPayment(ClientData clientData, Money amount){
		//TODO validate
		
		Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString() );   //AggregateId.generate();
		publisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Payment(aggregateId, clientData, amount);
	}
}
