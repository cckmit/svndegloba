package com.degloba.persones.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 
 * @author degloba
 *
 * @category command en el contexte Axon
 */
@Value
public class RequestPrivateAddressAssignmentCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}