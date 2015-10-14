
package com.degloba.ecommerce.sales.invoicing;

import com.degloba.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.purchase.Purchase;
import com.degloba.ecommerce.sales.purchase.PurchaseItem;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

@DomainFactory
public class InvoiceRequestFactory {

	public InvoiceRequest create(Client client, Purchase... purchases) {
		InvoiceRequest request = new InvoiceRequest(client.generateSnapshot());
		
		for (Purchase purchase : purchases) {
			if (! purchase.isPaid())
				throw new DomainOperationException(purchase.getAggregateId(), "Purchase is not paid");
			
			for (PurchaseItem item : purchase.getItems()) {
				request.add(new RequestItem(item.getProductData(), item.getQuantity(), item.getTotalCost()));
			}
		}
		
		return request;
	}

}
