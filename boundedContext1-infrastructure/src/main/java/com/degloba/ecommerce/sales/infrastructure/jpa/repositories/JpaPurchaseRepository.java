package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaPurchaseRepository extends EntityRepositoryJpa<Purchase> implements IPurchaseRepository{

	@Override
	public Purchase load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Purchase entity) {
		// TODO Auto-generated method stub
		
	}

}