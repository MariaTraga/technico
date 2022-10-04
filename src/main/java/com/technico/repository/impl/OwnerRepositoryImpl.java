package com.technico.repository.impl;

import com.technico.repository.OwnerRepository;
import com.technico.model.Owner;
import jakarta.persistence.EntityManager;



public class OwnerRepositoryImpl extends DBRepositoryImpl<Owner,Long> implements OwnerRepository{
	

	public OwnerRepositoryImpl(EntityManager entityManager) {
		 super(entityManager);
	 }

	@Override
	public String getEntityClassName() {
		return Owner.class.getName();
	}

	@Override
	public Class<Owner> getEntityClass() {
		return Owner.class;
	}
}
