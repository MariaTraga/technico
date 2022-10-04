package com.technico.service;

import com.technico.exception.OwnerException;
import com.technico.model.Owner;

import jakarta.persistence.EntityManager;

public class OwnerService {

	private EntityManager entityManager;

	public OwnerService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void addOwner(Owner owner) throws OwnerException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(owner);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new OwnerException("Owner has not been saved");
		}
	}
}