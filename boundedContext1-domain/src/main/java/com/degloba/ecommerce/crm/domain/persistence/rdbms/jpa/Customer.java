package com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.ecommerce.crm.domain.events.CustomerStatusChangedEvent;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

/**
 * Client
 * 
 * @author degloba
 *
 */
@Entity
@AggregateRoot
public class Customer extends BaseAggregateRoot{

	private static final long serialVersionUID = 1L;

/*
 * Tipus de client
 */
	public enum CustomerStatus{
		STANDARD, VIP, PLATINUM
	}
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	
	public void changeStatus(CustomerStatus status){
		if (status.equals(this.status))
			return;
		
		this.status = status;
		
		//Sample Case: give 10% rebate for all draft orders - communication via events with different Bounded Context to achieve decoupling
		////////eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}

}
