package com.degloba.ecommerce.crm.application.commands;

import java.io.Serializable;

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Customer.CustomerStatus;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.cqrs.command.annotations.Command;


/**
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Command
public class ChangeCustomerStatusCommand implements Serializable{

	private AggregateId customerId;
	
	private CustomerStatus status;

	public ChangeCustomerStatusCommand(AggregateId customerId, CustomerStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public AggregateId getCustomerId() {
		return customerId;
	}

	public CustomerStatus getStatus() {
		return status;
	}
	
	
}
