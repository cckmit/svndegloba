package com.degloba.lloguers.application.services;

import javax.inject.Inject;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


public class LloguerService implements ILloguerService {
	
/*	@Inject
	private IRentRepository rentRepositoryJpa;*/
	
	
	@Inject
	private ILloguerRepository rentRepositoryObjectify;



	@Override
	public void createCategory(Category category) {
		rentRepositoryObjectify.create(category);
	}
	
/*	Si l'Entitat es Nosql/datastoreGoogle (Api Objectify)
 * @Override
	public void createCategory(Category category) {
		categoryRepositoryObjectify.create(category);
	}*/

	
/*	public IRentRepository getCategoryRepositoryJpa() {
		return rentRepositoryJpa;
	}

	public void setCategoryRepositoryJpa(IRentRepository rentRepositoryJpa) {
		this.rentRepositoryJpa = rentRepositoryJpa;
	}
*/
	@Override
	public void createPhoto(Foto foto) {
		rentRepositoryObjectify.create(foto);
	}

	@Override
	public void createSubcategory(Subcategory subcategory) {
		rentRepositoryObjectify.save(subcategory);

	}

	@Override
	public void createOwner(Propietari propietari) {
		rentRepositoryObjectify.create(propietari);

	}

	public ILloguerRepository getRentRepositoryObjectify() {
		return rentRepositoryObjectify;
	}

	public void setRentRepositoryObjectify(ILloguerRepository rentRepositoryObjectify) {
		this.rentRepositoryObjectify = rentRepositoryObjectify;
	}
	
	

}