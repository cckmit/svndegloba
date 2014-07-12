package com.degloba.boundedContext.webui.JSF;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

// JSF
import javax.faces.context.FacesContext;
import javax.inject.Inject;



// Entitat Domini
import com.degloba.boundedContext.domain.Modalpanel;


// Finder (CQRS)
import com.degloba.boundedContext.readmodel.ModalpanelsFinder;


public class ModalPanels {
	
	private static final Logger log = Logger.getLogger(ModalPanels.class.getName());
	
    @Inject
    private ModalpanelsFinder modalpanelsFinder;
    
	
	private  ArrayList<Modalpanel> items = new ArrayList<Modalpanel>();
	Modalpanel mp;

	FacesContext context;
	ResourceBundle bundle;


	public ModalPanels() {
		super();

		context  = FacesContext.getCurrentInstance();
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
	}


	public List<Modalpanel> getItems() {
		return omplirPanelModals();		
	}


	public void setItems(ArrayList<Modalpanel> items) {
		this.items = items;
	}


	private List<Modalpanel> omplirPanelModals() {
		
		List<Modalpanel> panells = new ArrayList<Modalpanel>(); 
		
		try {
			
			List<Modalpanel> rs = modalpanelsFinder.findAll();
			
			log.info("Modalpanel count : " + rs.size());
    	
			Iterator<Modalpanel> imp=rs.iterator();
	        while (imp.hasNext())
	  		{
	        	Modalpanel mp1 = (Modalpanel) imp.next();
	        	
	    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
				
		  		mp = new Modalpanel();
		  		//mp.setId(mp1.getModalpanelid());
		  		mp.setTitol(bundle.getString("titolPanelModal." + mp1.getModalpanelid().toString()));
		  		//mp.setDefinicio(bundle.getString("definicioPanelModal." + mp1.getModalpanelid().toString()));
				
				panells.add(mp);
	  		}
                
		}	catch (Exception ex) {
			    // handle any errors
		}
		
		return panells;
	}


	public ModalpanelsFinder getModalpanelsFinder() {
		return modalpanelsFinder;
	}


	public void setModalpanelsFinder(ModalpanelsFinder modalpanelsFinder) {
		this.modalpanelsFinder = modalpanelsFinder;
	}
	
	
}

