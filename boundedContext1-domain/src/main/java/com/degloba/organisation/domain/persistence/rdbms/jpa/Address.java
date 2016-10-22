package com.degloba.organisation.domain.persistence.rdbms.jpa;

import com.degloba.domain.IValueObject;

import javax.persistence.Embeddable;


@Embeddable
public class Address implements IValueObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String province;
    private String city;
    private String detail;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
