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
/**
 * 
 */
package com.degloba.ecommerce.crm.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//import pl.com.bottega.ddd.annotations.domain.AggregateRoot;
import com.degloba.domain.BaseAggregateRoot;
import com.degloba.ecommerce.canonicalmodel.events.CustomerStatusChangedEvent;

/**
 * @author Slawek
 *
 */
@Entity
//@AggregateRoot
public class Customer extends BaseAggregateRoot{

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
		eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}
}
