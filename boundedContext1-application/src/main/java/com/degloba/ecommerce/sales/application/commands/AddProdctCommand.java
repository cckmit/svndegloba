package com.degloba.ecommerce.sales.application.commands;

import com.google.appengine.api.datastore.Key;

import com.degloba.cqrs.command.annotations.Command;


@Command
public class AddProdctCommand {

	private Key orderId;
	private Key productId;
	private int quantity;
	
	public AddProdctCommand(Key orderId, Key productId,
			int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public Key getOrderId() {
		return orderId;
	}
	
	public Key getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}