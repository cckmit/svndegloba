package com.degloba.domain.entity;

import javax.persistence.Entity;

import com.degloba.domain.AbstractEntity;
import com.degloba.domain.BaseAggregateRoot;

@Entity
public class MyEntity extends BaseAggregateRoot {
//public class MyEntity	extends BaseAggregateRoot{
    private String name;

    public MyEntity() {
    	this.name="prova";
    }

    public MyEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String[] businessKeys() {
        return new String [] {"name"};
    }



}
