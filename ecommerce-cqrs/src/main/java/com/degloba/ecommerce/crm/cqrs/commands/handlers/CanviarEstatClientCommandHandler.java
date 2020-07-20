package com.degloba.ecommerce.crm.cqrs.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.commands.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.commands.handlers.ICommandHandler;
import com.degloba.ecommerce.crm.cqrs.commands.CanviarEstatClientCommand;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;


/**
 * @author degloba
 * 
 * @category Modifica l'estat d'un {@link Client} a partir del {@link CanviarEstatClientCommand}
 *
 */
@CommandHandlerAnnotation
public class CanviarEstatClientCommandHandler implements ICommandHandler<CanviarEstatClientCommand,Boolean>{

	@Inject
	private ICrmRepository crmRepository; 
	
	@Override
	public Boolean handle(CanviarEstatClientCommand command) {
		Client client = crmRepository.get(Client.class, command.getClientId());
		client.canviaEstatClient(command.getEstatClient());
		crmRepository.save(client);		
		return true;
	}

}
