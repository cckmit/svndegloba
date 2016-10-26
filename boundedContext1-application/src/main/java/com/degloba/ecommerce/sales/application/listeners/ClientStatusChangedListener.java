package com.degloba.ecommerce.sales.application.listeners;

import javax.inject.Inject;

// CQRS
import com.degloba.cqrs.query.PaginatedResult;

// Ecommerce


import com.degloba.ecommerce.crm.domain.events.CustomerStatusChangedEvent;
import com.degloba.ecommerce.sales.application.services.DiscountingService;
import com.degloba.ecommerce.sales.cqrs.readmodel.finders.ISalesFinder;
import com.degloba.ecommerce.sales.orders.OrderQuery;
import com.degloba.ecommerce.sales.orders.dtos.OrderDto;
// Domain
import com.degloba.domain.sharedkernel.Money;


// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Applies discount 
 * 
 * @author degloba
 *
 */
@EventListeners
public class ClientStatusChangedListener {

	@Inject
	private DiscountingService discountingService;
	
	@Inject
	private ISalesFinder salesFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		OrderQuery orderQuery = new OrderQuery(null, event.getCustomerId());
		PaginatedResult<OrderDto> orders = salesFinder.query(orderQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}

	private Money calculateDiscout(long customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
