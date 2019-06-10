package com.degloba.ecommerce.vendes.facturacio.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.TipusProducte;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Tax;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * @category Política d'impostos
 * 
 * @author degloba
 *
 */
@DomainPolicy
public interface IImpostPolicy {	

	/**
	 * calcula impostos per tipus de producte segons el valor net
	 * 
	 * @param tipusProducte
	 * @param net
	 * @return
	 */
	public Tax calculateTax(TipusProducte tipusProducte, Money net);

}
