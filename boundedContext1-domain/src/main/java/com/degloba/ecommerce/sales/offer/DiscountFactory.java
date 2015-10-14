package com.degloba.ecommerce.sales.offer;

import com.degloba.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.offer.discounts.QuantityDiscount;

@DomainFactory
public class DiscountFactory {

	public DiscountPolicy create(Client client) {
		// TODO explore domain rules
		return new QuantityDiscount(20, 3);//20% for over 3 items
	}

}
