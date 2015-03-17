package com.degloba.casino.compres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.annotations.DomainFactory;
import com.degloba.casino.clients.Client;
import com.degloba.domain.sharedkernel.Money;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;


@DomainFactory
public class CompraFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	/*@Inject
	private DomainEventPublisher domainEventPublisher;*/

	/**
	 * 
	 * @param orderId correlation id - correlates purchases and reservations  
	 * @param client
	 * @param offer
	 * @return
	 */
	public Compra create(AggregateId orderId, Client client, Oferta oferta){
		if (! canPurchse(client, oferta.getAvailabeItems()))
			//throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
throw null;
			
		ArrayList<CompraItem> items = new ArrayList<CompraItem>(oferta.getAvailabeItems().size());
		Money purchaseTotlCost = Money.ZERO;

		for (OfertaItem item : oferta.getAvailabeItems()) {
			CompraItem purchaseItem = new CompraItem(item.getProductData(), item.getQuantity(), item.getTotalCost());
			items.add(purchaseItem);
			purchaseTotlCost = purchaseTotlCost.add(purchaseItem.getTotalCost());
		}

		Compra compra = new Compra(orderId, client.generateSnapshot(),
				items, new Date(), false, purchaseTotlCost);

		spring.autowireBean(compra);

		return compra;
	}

	private boolean canPurchse(Client client, List<OfertaItem> availabeItems) {
		return true;//TODO explore domain rules
	}


}