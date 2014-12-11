package com.degloba.boundedContext.domain.modules.client;

// JPA
import javax.persistence.Entity;

// BaseEntity = NO aggregate


import domain.annotations.DomainEntity;
import domain.support.BaseAggregateRoot;

@Entity
@DomainEntity
public class Empresa extends BaseAggregateRoot {
	
	
}