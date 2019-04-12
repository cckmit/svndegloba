package com.degloba.gcm.domain.persistence.nosql.mongo.spring;

import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

/** 
 *
 * Entitat/Document (MongoDB) : GCM Token (Android devices) 
 * 
 * @author pere
 *
 **/ 
@Document public class GCMTokenRegister { 
	 @Id private String GCMTokenRegisterId; 
	 
	 private String token; 

	 
	 public GCMTokenRegister(String token) { 
		 this.token = token; 
		 } 
	 
	 public String getGCMTokenRegisterId() { 
		 return GCMTokenRegisterId; 
		 } 
		 
		 public void setGCMTokenRegisterId(final String GCMTokenRegisterId) { 
			 this.GCMTokenRegisterId = GCMTokenRegisterId; 
		 } 
		 
		 public String getToken() { 
			 return token; 
		 } 
		 
		 public void setToken(final String token) { 
			 this.token = token; 
		 } 
		 		 		
		 
		 } 