package com.degloba.ecommerce.enviaments.cqrs.commands;

import java.io.Serializable;

import com.degloba.cqrs.command.annotations.ICommand;
import com.degloba.persistence.rdbms.jpa.AggregateId;


/**
 * @category
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@ICommand
public class EntregarEnviamentCommand implements Serializable {

    private final AggregateId enviamentId;

    public EntregarEnviamentCommand(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

    public AggregateId getEnviamentId() {
        return enviamentId;
    }
}
