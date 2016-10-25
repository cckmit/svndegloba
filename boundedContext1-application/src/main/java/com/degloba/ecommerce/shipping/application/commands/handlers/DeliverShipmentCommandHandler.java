package com.degloba.ecommerce.shipping.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.CommandHandler;
import com.degloba.ecommerce.shipping.application.commands.DeliverShipmentCommand;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShippingRepository;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.Shipment;



@CommandHandlerAnnotation
public class DeliverShipmentCommandHandler implements CommandHandler<DeliverShipmentCommand, Void> {

    @Inject
    private IShippingRepository shippingRepository;

    @Override
    public Void handle(DeliverShipmentCommand command) {
        Shipment shipment = shippingRepository.get(Shipment.class,command.getShipmentId());
        shipment.deliver();
        return null;
    }
}
