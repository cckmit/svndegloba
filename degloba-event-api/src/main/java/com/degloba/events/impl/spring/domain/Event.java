package com.degloba.events.impl.spring.domain;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author degloba
 *
 * @category Events
 * 
 * @implNote Spring
 * 
 */
public class Event<T> extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter	private T what;
	
	@Getter @Setter	protected boolean success;

	public Event(Object source, T what, boolean success) {
		super(source);
		this.what = what;
		this.success = success;
	}
 

    
}