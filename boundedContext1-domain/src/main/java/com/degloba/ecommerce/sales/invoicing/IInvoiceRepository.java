package com.degloba.ecommerce.sales.invoicing;

import com.degloba.annotations.DomainRepository;

/**
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IInvoiceRepository {

	/**
	 * @param invoice
	 */
	public void save(Invoice invoice);

}
