package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;
import com.degloba.lloguers.facade.jpa.SubcategoryFacade;


public class SubcategoryFacadeImpl implements SubcategoryFacade {

    @Inject
    protected ILloguerService subcategoryApplication;

    public SubcategoryFacadeImpl(ILloguerService application) {
        this.subcategoryApplication = application;
    }
   
	public SubcategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createSubcategory(SubCategoria SubCategoria) {
		// TODO Auto-generated method stub
		subcategoryApplication.createSubcategory(SubCategoria);
	}

	public ILloguerService getSubcategoryApplication() {
		return subcategoryApplication;
	}

	public void setSubcategoryApplication(ILloguerService subcategoryApplication) {
		this.subcategoryApplication = subcategoryApplication;
	}
	
		
}
