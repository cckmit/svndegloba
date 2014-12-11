package com.degloba.boundedContext.infrastructure.repositories;

import java.io.Serializable;
import java.util.List;

import infrastructure.seedwork.Repository;

import com.degloba.boundedContext.domain.casino.ICasinoClasseRepository;

import domain.seedwork.Entity;
import domain.seedwork.IRepository;
import domain.support.BaseEntity;
import domain.support.CriteriaQuery;
import domain.support.ExampleSettings;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;
import domain.support.NamedQuery;
import domain.support.SqlQuery;


public class CasinoClasseRepository<E extends BaseEntity> extends Repository<E>
			implements ICasinoClasseRepository {


	@Override
	public IRepository<BaseEntity> CreateRepository() {
		// TODO Auto-generated method stub
		IRepository<BaseEntity> rep =  (IRepository<BaseEntity>) new Repository();
		return rep;
	}
	
	
	@Override
	public void add(BaseEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseEntity save(BaseEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BaseEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BaseEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseEntity update(BaseEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Class<?> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <E> E get(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E load(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getUnmodified(Class<E> clazz, E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseEntity getByBusinessKeys(Class<BaseEntity> clazz,
			NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseEntity> findAll(Class<BaseEntity> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> CriteriaQuery createCriteriaQuery(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NamedQuery createNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SqlQuery createSqlQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <E extends Entity, E2 extends E> List<E> findByExample(E2 example,
			ExampleSettings<E> settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> findByProperty(Class<E> clazz, String propertyName,
			Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> findByProperties(Class<E> clazz,
			NamedParameters properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryStringOfNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


}