package com.degloba.ecommerce.vendes.application.events.guava.eventbus;



import com.degloba.ecommerce.vendes.application.events.CashPurchaseEvent;
import com.degloba.ecommerce.vendes.application.events.CreditPurchaseEvent;
import com.degloba.ecommerce.vendes.application.events.NoSubscriberEvent;
import com.degloba.event.guava.eventbus.events.SimpleEvent;

import com.google.common.eventbus.EventBus;

public class EventPublisher {

	    EventBus eventBus;

	    public EventPublisher(EventBus eventBus) {
	        this.eventBus = eventBus;
	    }

	    public void createCashPurchaseEvent(String description, long amount) {
	        eventBus.post(new CashPurchaseEvent(amount, description));
	    }

	    public void createCreditPurchaseEvent(String item, String ccNumber, long amount) {
	        eventBus.post(new CreditPurchaseEvent(amount, ccNumber, item));
	    }

	    public void createSimpleEvent(String eventName) {
	        eventBus.post(new SimpleEvent(eventName));
	    }

	    public void createNoSubscribedEvent() {
	        eventBus.post(new NoSubscriberEvent());
	    }

}