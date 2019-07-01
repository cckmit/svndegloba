package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;
import com.degloba.lloguers.facade.objectify.SubcategoriaFacade;


public class SubcategoriaFacadeImpl implements SubcategoriaFacade {

    @Inject
    protected ILloguerService subcategoriaApplication;

    public SubcategoriaFacadeImpl(ILloguerService application) {
        this.subcategoriaApplication = application;
    }
   
	public SubcategoriaFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaSubcategoria(SubCategoria Subcategoria) {
		// TODO Auto-generated method stub
		subcategoriaApplication.creaSubcategoria(Subcategoria);
	}

	public ILloguerService getSubcategoriaApplication() {
		return subcategoriaApplication;
	}

	public void setSubcategoriaApplication(ILloguerService subcategoriaApplication) {
		this.subcategoriaApplication = subcategoriaApplication;
	}
	
		
}