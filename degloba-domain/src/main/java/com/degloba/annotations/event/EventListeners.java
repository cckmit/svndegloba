package com.degloba.annotations.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author degloba
 * 
 */
@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}