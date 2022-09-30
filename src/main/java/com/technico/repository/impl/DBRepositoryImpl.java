package com.technico.repository.impl;

import java.util.List;
import java.util.Optional;

import com.technico.model.Property;
import com.technico.repository.Repository;

import jakarta.persistence.EntityManager;

public abstract class DBRepositoryImpl<D, ID> implements Repository<D, ID> {

	private EntityManager entityManager;

	public DBRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public abstract String getEntityClassName();

	public abstract Class<D> getEntityClass();

	@Override
	public Optional<D> create(D entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return Optional.of(entity);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public Optional<D> read(ID id) {
		D entity = entityManager.find(getEntityClass(), id);
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	public List<D> readAll() {
		List<D> listOfDomains = entityManager
				.createQuery("SELECT d FROM " + getEntityClassName() + " d", getEntityClass()).getResultList();
		return listOfDomains;
	}

	public Optional<D> update(D entity){
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return Optional.of(entity);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	public boolean delete(ID id) {
		D entity = entityManager.find(getEntityClass(), id);
		if (entity != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(entity);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	

}
