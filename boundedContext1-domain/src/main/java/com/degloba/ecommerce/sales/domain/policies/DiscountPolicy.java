package com.degloba.ecommerce.sales.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Discount;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * Interficies Politica de Descompte
 *  
 * @author degloba
 */
@DomainPolicy
public interface DiscountPolicy {

	public Discount applyDiscount(Product product, int quantity, Money reularCost);
}
