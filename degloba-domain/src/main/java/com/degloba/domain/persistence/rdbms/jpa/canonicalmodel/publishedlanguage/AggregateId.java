package com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;


/*
 * https://github.com/BottegaIT/ddd-leaven-v2
 */

@SuppressWarnings("serial")
@Embeddable
public class AggregateId implements Serializable {
	
	@Column(name = "aggregateId", length = 255, unique=true, nullable=false)
    private String aggregateId; 
    
    public AggregateId(String aggregateId) {                
    	Validate.notNull(aggregateId);                
    	this.aggregateId = aggregateId;        
    	}        
    
    public AggregateId() {        
    		
    	}          
    
    public String getId() {
		return aggregateId;
	}
    
    public static AggregateId generate(){                
    		return new AggregateId(UUID.randomUUID().toString());        
    		}      
    
     
    @Override        
    public int hashCode() {                
    		return aggregateId.hashCode();        
    		}        
    	
    @Override        
    public boolean equals(Object obj) {                
    		if (this == obj)                        
    			return true;                
    		if (obj == null)                        
    			return false;                
    		if (getClass() != obj.getClass())                        
    			return false;                
    		AggregateId other = (AggregateId) obj;                
    		if (aggregateId == null) {                        
    			if (other.aggregateId != null)                                
    				return false;                
    			} else if (!aggregateId.equals(other.aggregateId))                        
    				return false;                
    		return true;        
    		}        
    
    @Override        
    public String toString() {                
    		return aggregateId;        
    }

	public AggregateId getAggregateId() {
		return this;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

}
