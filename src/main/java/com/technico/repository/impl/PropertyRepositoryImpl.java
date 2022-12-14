package com.technico.repository.impl;

import java.util.List;
import java.util.Optional;

import com.technico.model.Property;
import com.technico.repository.PropertyRepository;

import jakarta.persistence.EntityManager;

public class PropertyRepositoryImpl extends DBRepositoryImpl<Property, Long> implements PropertyRepository {

	EntityManager entityManager;

	public PropertyRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public String getEntityClassName() {
		return Property.class.getName();
	}

	@Override
	public Class<Property> getEntityClass() {
		return Property.class;
	}

	@Override
	public Optional<Property> readByIdNumber(String propertyIdNumber) {
		List<Property> propertyList = entityManager.createQuery(
				"SELECT p from " + getEntityClassName() + " p WHERE p.propertyIdNumber = :propertyIdNumber",
				Property.class).setParameter("propertyIdNumber", propertyIdNumber).getResultList();
		
		return propertyList != null ? propertyList.stream().findFirst() : Optional.empty();
	}

	@Override
	public List<Property> readByOwnerVAT(String ownerVAT) {
		List<Property> propertyList = entityManager
				.createQuery("SELECT p from " + getEntityClassName() + " p WHERE p.owner.ownerVAT = :ownerVAT",
						Property.class)
				.setParameter("ownerVAT", ownerVAT).getResultList();

		return propertyList;
	}

	@Override
	public boolean deleteSafe(Long id) {
		Property property = read(id).orElse(null);
		property.setDeleted(true);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(property);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
