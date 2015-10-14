package com.degloba.ecommerce.shipping.api.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import command.annotations.Command;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@SuppressWarnings("serial")
@Command
public class DeliverShipmentCommand implements Serializable {

    private final Key shipmentId;

    public DeliverShipmentCommand(Key shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Key getShipmentId() {
        return shipmentId;
    }
}
