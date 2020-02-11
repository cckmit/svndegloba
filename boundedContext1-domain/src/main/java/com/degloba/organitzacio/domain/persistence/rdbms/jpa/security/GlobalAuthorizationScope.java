package com.degloba.organitzacio.domain.persistence.rdbms.jpa.security;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 */
@Entity
@DiscriminatorValue("GLOBAL")
public class GlobalAuthorizationScope extends AuthorizationScope {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GlobalAuthorizationScope() {
        super("GLOBAL");
    }

    @Override
    public String toString() {
        return "GlobalAuthorizationScope{}";
    }

    @Override
    public AuthorizationScope getParent() {
        return null;
    }

    @Override
    public Set<AuthorizationScope> getChildren() {
        return Collections.emptySet();
    }

    public static final GlobalAuthorizationScope get() {
        GlobalAuthorizationScope result = getRepository().createCriteriaQuery(GlobalAuthorizationScope.class).singleResult();
        if (result == null) {
            result = new GlobalAuthorizationScope();
            result.save();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GlobalAuthorizationScope)) {
            return false;
        }
        GlobalAuthorizationScope that = (GlobalAuthorizationScope) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
