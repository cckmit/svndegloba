package com.degloba.ecommerce.vendes.eventsourcing.events.eventlisteners;

import javax.inject.Inject;

import com.degloba.ecommerce.comandes.eventsourcing.events.ComandaEnviadaEvent;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;

import com.degloba.ecommerce.facturacio.vendes.domain.services.AssesorFiscalService;
import com.degloba.ecommerce.facturacio.vendes.domain.services.BookKeeperService;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.facturacio.domain.factories.PeticionsFacturaFactory;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.events.annotations.EventListenerAnnotation;
import com.degloba.events.annotations.EventListenersAnnotation;

/**
 * @category Listener d'events implementat amb la implementació d'events degloba/Spring
 * 
 * @author degloba
 *
 */
@EventListenersAnnotation
public class BookKeepingListener {

	@Inject
	private BookKeeperService bookKeeper;
	
	@Inject
	private IVendaRepository vendaRepository;
		
	@Inject
	private AssesorFiscalService assesorFiscalService;
	
	
	@Inject
	private PeticionsFacturaFactory peticionsFacturaFactory;
	
	@EventListenerAnnotation
	public void handle(ComandaEnviadaEvent event){
		// recuperem la compra a partir de l'Id de l'ordre
		Compra compra = vendaRepository.get(Compra.class, event.getComandaId());
		
		// recuperem el {@link Client} a partir de {@link ClientData}
		Client client = vendaRepository.get(Client.class, compra.getClientData().getAggregateId());
		
		PeticioFactura request  = peticionsFacturaFactory.create(client, compra); 
		
		Factura factura = bookKeeper.emet(request, assesorFiscalService.suggereixMillorImpost(client));
		
		vendaRepository.save(factura);
	}
}
