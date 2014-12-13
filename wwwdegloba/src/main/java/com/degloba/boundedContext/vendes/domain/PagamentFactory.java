package com.degloba.boundedContext.vendes.domain;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import domain.annotations.DomainFactory;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.canonicalmodel.publishedlanguage.ClientData;
import domain.sharedkernel.Money;
import domain.sharedkernel.exceptions.DomainOperationException;
import domain.support.DomainEventPublisher;

@DomainFactory
public class PagamentFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
/*	@Inject
	private DomainEventPublisher domainEventPublisher;*/

	
	public Pagament createPagament(ClientData clientData, Money amount){
		//TODO validate
/*
		AggregateId aggregateId = AggregateId.generate();
		domainEventPublisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Modalpanel(aggregateId, clientData, amount);*/
		return null;
	}

}