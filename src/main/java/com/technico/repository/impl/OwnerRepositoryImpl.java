package com.technico.repository.impl;

import com.technico.repository.OwnerRepository;

import java.util.Optional;

import com.technico.exception.OwnerException;
import com.technico.model.Owner;
import jakarta.persistence.EntityManager;



public class OwnerRepositoryImpl extends DBRepositoryImpl<Owner,Long> implements OwnerRepository{
	
	EntityManager entityManager;
	public OwnerRepositoryImpl(EntityManager entityManager) {
		 super(entityManager);
		 this.entityManager = entityManager;
	 }

	@Override
	public String getEntityClassName() {
		return Owner.class.getName();
	}

	@Override
	public Class<Owner> getEntityClass() {
		return Owner.class;
	}
	
	public Optional<Owner> readVAT(String ownerVAT) {
		Owner owner = entityManager
				.createQuery("SELECT o from "+getEntityClassName()+" o WHERE o.ownerVAT = :ownerVAT"
				,Owner.class)
				.setParameter("ownerVAT",ownerVAT)
				.getSingleResult();

		return owner != null ? Optional.of(owner) : Optional.empty();
	}
	
	public Optional<Owner> readEmail(String email) {
		Owner owner = entityManager
				.createQuery("SELECT o from "+getEntityClassName()+" o WHERE o.email = :email"
				,Owner.class)
				.setParameter("email",email)
				.getSingleResult();

		return owner != null ? Optional.of(owner) : Optional.empty();
	}

	@Override
	public Optional<Owner> readAddress(String address) throws OwnerException {
		Owner owner = entityManager
				.createQuery("SELECT o from "+getEntityClassName()+" o WHERE o.address = :address"
				,Owner.class)
				.setParameter("address",address)
				.getSingleResult();

		return owner != null ? Optional.of(owner) : Optional.empty();
	}

	@Override
	public Optional<Owner> readPassword(String password) throws OwnerException {
		Owner owner = entityManager
				.createQuery("SELECT o from "+getEntityClassName()+" o WHERE o.password = :password"
				,Owner.class)
				.setParameter("password",password)
				.getSingleResult();

		return owner != null ? Optional.of(owner) : Optional.empty();
	}
}