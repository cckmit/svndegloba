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
package com.degloba.ecommerce.sales.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.annotations.ApplicationService;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.api.command.OrderDetailsCommand;
import com.degloba.ecommerce.sales.api.service.OfferChangedExcpetion;
import com.degloba.ecommerce.sales.api.service.OrderingService;
import com.degloba.ecommerce.sales.client.Client;
//import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.offer.DiscountFactory;
import com.degloba.ecommerce.sales.offer.DiscountPolicy;
import com.degloba.ecommerce.sales.offer.Offer;
import com.degloba.ecommerce.sales.payment.Payment;
//import pl.com.bottega.ecommerce.sales.domain.payment.PaymentRepository;
import com.degloba.ecommerce.sales.productscatalog.Product;
//import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import com.degloba.ecommerce.sales.purchase.Purchase;
import com.degloba.ecommerce.sales.purchase.PurchaseFactory;
//import pl.com.bottega.ecommerce.sales.domain.purchase.PurchaseRepository;
import com.degloba.ecommerce.sales.reservation.Reservation;
import com.degloba.ecommerce.sales.reservation.ReservationFactory;
import com.degloba.domain.EntityRepository;
//import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

import com.degloba.ecommerce.system.application.SystemUser;

/**
 * Ordering Use Case steps<br>
 * Each step is a Domain Story<br>
 * <br>
 * Notice that application language is different (simpler) than domain language, ex: we don'nt want to exposure domain concepts like Purchase and Reservation to the upper layers, we hide them under the Order term  
 * <br>
 * Technically App Service is just a bunch of procedures, therefore OO principles (ex: CqS, SOLID, GRASP) does not apply here  
 *
 * @author Slawek
 */
@ApplicationService
public class OrderingServiceImpl implements OrderingService {

/*	@Inject
	private SystemUser systemUser;*/
	
	@Inject
	private EntityRepository entityRepository;
	
	/*@Inject
	private ClientRepository clientRepository;

	@Inject
	private ReservationRepository reservationRepository;
*/
	@Inject
	private ReservationFactory reservationFactory;

	@Inject
	private PurchaseFactory purchaseFactory;

	/*@Inject
	private PurchaseRepository purchaseRepository;
	
	@Inject
	private ProductRepository productRepository;*/
	
	/*@Inject 
	private PaymentRepository paymentRepository;
*/
	@Inject
	private DiscountFactory discountFactory;
	
	@Inject
	private SuggestionService suggestionService;

	// @Secured requires BUYER role
	public Key createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		entityRepository.save(reservation);
		return reservation.getAggregateId();
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Load reservation by orderId<br>
	 * Load product by productId<br>
	 * Check if product is not available<br>
	 * -if so, than suggest equivalent for that product based on client<br>
	 * Reservation add product by given quantity
	 */
	@Override
	public void addProduct(Key orderId, Key productId,
			int quantity) {
		Reservation reservation = entityRepository.load(Reservation.class,orderId);
		
		Product product = entityRepository.load(Product.class,productId);
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
			
		reservation.add(product, quantity);
		
		entityRepository.save(reservation);
	}
	
	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
	public Offer calculateOffer(Key orderId) {
		Reservation reservation = entityRepository.load(Reservation.class,orderId);

		DiscountPolicy discountPolicy = discountFactory.create(loadClient());
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		return reservation.calculateOffer(discountPolicy);
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Load reservation by orderId<br>
	 * Check if reservation is closed - if so, than Error<br>
	 * Generate new offer from reservation using discount created per client<br>
	 * Check if new offer is not the same as seen offer using delta = 5<br>
	 * Create purchase per client based on seen offer<br>
	 * Check if client can not afford total cost of purchase - if so, than Error<br>
	 * Confirm purchase<br>
	 * Close reservation<br>
	 */
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)//highest isolation needed because of manipulating many Aggregates
	public void confirm(Key orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion {
		Reservation reservation = entityRepository.load(Reservation.class,orderId);
		if (reservation.isClosed())
			throw new DomainOperationException(reservation.getAggregateId(), "reservation is already closed");
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		Offer newOffer = reservation.calculateOffer(
									discountFactory.create(loadClient()));
		
		/*
		 * Sample pattern: Client Tier sends back old VOs, Server generates new VOs based on Aggregate state<br>
		 * Notice that this VO is not stored in Repo, it's stored on the Client Tier. 
		 */
		if (! newOffer.sameAs(seenOffer, 5))//TODO load delta from conf.
			throw new OfferChangedExcpetion(reservation.getAggregateId(), seenOffer, newOffer);
		
		Client client = loadClient();//create per logged client, not reservation owner					
		Purchase purchase = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);
				
		if (! client.canAfford(purchase.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");
		
		entityRepository.save(purchase);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 
		
		/*
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 */
		Payment payment = client.charge(purchase.getTotalCost());
		entityRepository.save(payment);
		
		purchase.confirm();	
		reservation.close();				
		
		entityRepository.save(reservation);
		entityRepository.save(client);
		
	}
	
	private Client loadClient() {
		return null;
		////////////////return entityRepository.load(systemUser.getDomainUserId());
	}
}
