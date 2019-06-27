package com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.EstatComanda;
import com.degloba.persistence.rdbms.jpa.AggregateId;

/**
 * @category DTO (Objecte de transferencia de dades ) d'una ordre
 * 
 * Una Comanda està formada per una llista de {@link Producte}
 * 
 *  * @author degloba
 *
 */
public class ComandaDto {

	private AggregateId comandaId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();
	private EstatComanda estatComanda;
	private Boolean confirmable;

	public AggregateId getComandaId() {
		return comandaId;
	}

	public void setComandaId(AggregateId comandaId) {
		this.comandaId = comandaId;
	}

	public List<OrderedProductDto> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductDto> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public EstatComanda getEstatComanda() {
		return estatComanda;
	}

	public void setEstatComanda(EstatComanda estatComanda) {
		this.estatComanda = estatComanda;
	}

	public Boolean getConfirmable() {
		return confirmable;
	}

	public void setConfirmable(Boolean confirmable) {
		this.confirmable = confirmable;
	}
}