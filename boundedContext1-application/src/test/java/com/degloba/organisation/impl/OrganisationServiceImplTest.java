package com.degloba.organisation.impl;


import com.degloba.organisation.application.service.IOrganisationService;
import com.degloba.organisation.application.service.OrganisationApplicationImpl;
import com.degloba.organisation.impl.OrganisationServiceTest;


public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected IOrganisationService createInstance() {
        return new OrganisationApplicationImpl(repository);
    }
 
    
}
