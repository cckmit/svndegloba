package com.degloba.gcm.domain.persistence.nosql.mongo.spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entitat/Document (MongoDB) : Tema
 * 
 * @author pere
 *
 */
@Document 
public class Topic { 
	 @Id private String topicId; 
	 
	 private String name; 
	 private String owner;
	 	 
	 public Topic(String name, String owner) { 
		 this.name = name; 
		 this.owner = owner; 
	} 
	 
	 public String getTopicId() { 
		return topicId; 
	} 
		 
	public void setTopicId(final String topicId) { 
		this.topicId = topicId; 
	} 
		 
	public String getName() { 
		return name; 
	} 
		 
	public void setName(final String name) { 
		this.name = name; 
	} 
		 
		 public String getOwner() { 
			 return owner; 
		 } 
		 
		 public void setOwner(final String owner) { 
			 this.owner = owner; 
			 } 
		 			 
		
		 } 
