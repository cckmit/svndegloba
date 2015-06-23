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
package com.degloba.ecommerce.sales.api.service;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.offer.Offer;

@SuppressWarnings("serial")
public class OfferChangedExcpetion extends RuntimeException {
	
	private Key orderId;
	private Offer seenOffer;
	private Offer newOffer;
	
	public OfferChangedExcpetion(Key orderId, Offer seenOffer,
			Offer newOffer) {
		this.orderId = orderId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public Key getOrderId() {
		return orderId;
	}
	
	public Offer getSeenOffer() {
		return seenOffer;
	}
	
	public Offer getNewOffer() {
		return newOffer;
	}

}