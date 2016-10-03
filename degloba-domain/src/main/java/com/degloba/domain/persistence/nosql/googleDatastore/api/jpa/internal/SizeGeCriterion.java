package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * Discover a collection attribute condition judging the number of records is greater than or equal to the specified value
 */
public class SizeGeCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value Property Value
     */
    public SizeGeCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" >= ");
    }
}
