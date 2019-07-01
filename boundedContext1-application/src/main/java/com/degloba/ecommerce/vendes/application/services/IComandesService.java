package com.degloba.ecommerce.vendes.application.services;



import com.degloba.ecommerce.vendes.application.commands.OrderDetailsCommand;
import com.degloba.ecommerce.vendes.application.exceptions.OfertaCanviadaException;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.persistence.rdbms.jpa.AggregateId;


/*
 *  @category Servei de comandes implementat amb RDBMS/JPA
 *  
 *  @author degloba
 */
public interface IComandesService {

	public AggregateId creaComanda();

	public void afegirProducte(AggregateId comandaId, AggregateId producteId, int quantitat);

	public Oferta calculateOffer(AggregateId comandaId);

	public void confirma(AggregateId comandaId, OrderDetailsCommand orderDetailsCommand, Oferta seenOffer)
			throws OfertaCanviadaException;
}