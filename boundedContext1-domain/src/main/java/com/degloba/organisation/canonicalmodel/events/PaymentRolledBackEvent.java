package com.degloba.organisation.canonicalmodel.events;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import com.degloba.annotations.event.Event;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@SuppressWarnings("serial")
@Event
public class PaymentRolledBackEvent implements Serializable{

	private Key paymentId;
	
	public PaymentRolledBackEvent(Key paymentId){
		this.paymentId = paymentId;
	}
	
	public Key getPaymentId() {
		return paymentId;
	}

}