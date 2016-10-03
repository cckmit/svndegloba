package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.NamedParameters;
import com.degloba.utils.Assert;

public class StartsWithTextCriterion extends BasicCriterion {

    private final String value;

    public StartsWithTextCriterion(String propName, String value) {
        super(propName);
        Assert.notBlank(value, "Value is null or blank!");
        this.value = value;
    }

    /**
     * Get Match value
     * @return  Match value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + " like " + getParamNameWithColon();
    }

    public NamedParameters getParameters() {
        return NamedParameters.create().add(getParamName(), value + "%");
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StartsWithTextCriterion)) {
            return false;
        }
        StartsWithTextCriterion that = (StartsWithTextCriterion) other;
        return new EqualsBuilder()
                .append(this.getPropName(), that.getPropName())
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPropName())
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return getPropName() + " like '" + value + "*'";
    }
}
