package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * Analyzing a collection of attributes the number of records is not equal to the specified value of the query
 */
public class SizeNotEqCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value  Property Value
     */
    public SizeNotEqCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" != ");
    }
}