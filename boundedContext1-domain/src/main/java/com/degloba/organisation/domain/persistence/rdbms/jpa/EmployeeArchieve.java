package com.degloba.organisation.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;


@Entity
public class EmployeeArchieve extends BaseAggregateRoot {
    
    
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"employee"};
    }
    
}