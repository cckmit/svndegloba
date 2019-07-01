package com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.ClientData;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.ecommerce.vendes.domain.events.PagamentRolledBackEvent;
import com.degloba.ecommerce.vendes.pagaments.domain.factories.PagamentsFactory;

import com.degloba.persistence.domain.sharedkernel.Money;

//@AggregateRoot
/**
 * 
 * @category Entitat que defineix un pagament
 * 
 * @author degloba
 *
 */
@Entity
public class Pagament extends BaseAggregateRoot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			

	@Embedded
	private ClientData clientData;
	
	@Embedded
	private Money quantitat;
	
	@Transient
	@Inject
	private PagamentsFactory pagamentsFactory;
	
	private Pagament(){}
	
	public Pagament(AggregateId aggregateId, ClientData clientData, Money quantitat) {
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.quantitat = quantitat;
	}

	public Pagament rollBack()
	{
		
		//TODO explore domain rules
		//eventPublisher.publish(new PagamentRolledBackEvent(getAggregateId()));
		
		return pagamentsFactory.creaPagament(clientData, quantitat.multiplyBy(-1));
	}


}