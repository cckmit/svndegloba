package com.degloba.organitzacio.webapp.controllers.impl.spring;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.degloba.security.spring.gae.security.AppRole;
import com.degloba.security.spring.gae.security.GaeUserAuthentication;
import com.degloba.security.spring.gae.users.GaeUser;
import com.degloba.security.spring.gae.users.UserRegistry;

//import java.util.logging.Logger;
import com.google.appengine.api.users.UserServiceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Luke Taylor
 */
@Controller
@RequestMapping(value = "/gae/register.htm")
public class RegistrationController {

	@Autowired
	private UserRegistry registry;
	
	private final Logger log = LoggerFactory.getLogger(getClass()); //Logger.getLogger(RegistrationController.class.getName());
	
	/*
	 * Model Spring MVC
	 */
	@RequestMapping(method = RequestMethod.GET)
	    public void getRegistre(Model model) {	
	        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());	
	        
	}
	
	@RequestMapping(method = RequestMethod.POST)
		public String register(@RequestParam("username")  String username, 
					@RequestParam("password")  String password) {   //@RequestBody  @RequestParam
		  
		
		log.debug("**************  Registre Usuari : ");
		
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		GaeUser currentUser = (GaeUser) authentication.getPrincipal();
		Set<AppRole> roles = EnumSet.of(AppRole.USER);

		if (UserServiceFactory.getUserService().isUserAdmin()) {
			roles.add(AppRole.ADMIN);
		}

		GaeUser user = new GaeUser(currentUser.getUserId(), currentUser.getNickname(),
				currentUser.getEmail(), username, password, roles,
				true);

		log.debug("**************  Registre Usuari : " + user);
		
		///////registry.registerUser(user);

		// Update the context with the full authentication
		SecurityContextHolder.getContext().setAuthentication(
				new GaeUserAuthentication(user, authentication.getDetails()));

		return "redirect:/gae";		 
		}

	/*@RequestMapping(method = RequestMethod.GET)
	public RegistrationForm registrationForm() {
		return new RegistrationForm();		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid RegistrationForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		GaeUser currentUser = (GaeUser) authentication.getPrincipal();
		Set<AppRole> roles = EnumSet.of(AppRole.USER);

		if (UserServiceFactory.getUserService().isUserAdmin()) {
			roles.add(AppRole.ADMIN);
		}

		GaeUser user = new GaeUser(currentUser.getUserId(), currentUser.getNickname(),
				currentUser.getEmail(), form.getForename(), form.getSurname(), roles,
				true);

		registry.registerUser(user);

		// Update the context with the full authentication
		SecurityContextHolder.getContext().setAuthentication(
				new GaeUserAuthentication(user, authentication.getDetails()));

		return "redirect:/home.htm";
	}*/
}