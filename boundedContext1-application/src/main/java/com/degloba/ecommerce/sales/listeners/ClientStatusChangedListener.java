/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.listeners;

import javax.inject.Inject;

import query.PaginatedResult;

import com.degloba.annotations.event.EventListener;
import com.degloba.annotations.event.EventListeners;
import com.degloba.ecommerce.canonicalmodel.events.CustomerStatusChangedEvent;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.internal.discounts.DiscountingService;
import com.degloba.ecommerce.sales.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.sales.readmodel.orders.OrderQuery;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Applies discount 
 * 
 * @author Slawek
 *
 */
@EventListeners
public class ClientStatusChangedListener {

	@Inject
	private DiscountingService discountingService;
	@Inject
	private OrderFinder orderFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		OrderQuery orderQuery = new OrderQuery(null, event.getCustomerId());
		PaginatedResult<OrderDto> orders = orderFinder.query(orderQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}

	private Money calculateDiscout(Key customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
