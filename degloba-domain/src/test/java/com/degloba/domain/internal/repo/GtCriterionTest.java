package com.degloba.domain.internal.repo;


import com.degloba.domain.internal.repo.GtCriterion;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GtCriterionTest {
    
    private GtCriterion instance;

    @Before
    public void setUp() {
        instance = new GtCriterion("name", "abc");
    }

    @Test
    public void testGetValue() {
        assertEquals("abc", instance.getValue());
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name > :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_name" + instance.hashCode(), "abc"), 
                instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        GtCriterion other = new GtCriterion("name", "abc");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }
    
}
