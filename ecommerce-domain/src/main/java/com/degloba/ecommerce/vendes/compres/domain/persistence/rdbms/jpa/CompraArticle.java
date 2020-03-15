package com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import javax.persistence.Entity;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.ProducteData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

/**
 * @category Els articles comprats contenen dades copiades en cas de procediment de catàleg 
 * i canvi de descompte
 * 
 * @author degloba
 *
 */
@ValueObject
@Entity
public class CompraArticle extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Embedded
	private ProducteData producteData;
	
	private int quantitat;	
	
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "purchaseTotalCost_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "purchaseTotalCost_currencyCode")) })
	private Money totalCost;
	
	private CompraArticle() {}
	
	public CompraArticle(ProducteData producteData, int quantitat, Money totalCost) {
		this.producteData = producteData;
		this.quantitat = quantitat;
		this.totalCost = totalCost;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public ProducteData getProducteData() {
		return producteData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}


		
}
