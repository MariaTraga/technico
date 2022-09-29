package com.technico.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.technico.exception.PropertyException;
import com.technico.model.Property;
import com.technico.repository.Repository;

import jakarta.persistence.EntityManager;

public class PropertyRepositoryImpl implements Repository<Property, Long> {

	//TODO Refactor exceptions to custom exceptions
	
	private final List<Property> listOfProperties;
	private EntityManager entityManager;
	
	public PropertyRepositoryImpl(EntityManager entityManager, List<Property> listOfProperties) {
		this.entityManager = entityManager;
		//TODO Should remove list
		this.listOfProperties = listOfProperties;
	}

	
	//TODO Refactor repository crud methods
	
	@Override
	public Property create(Property domain) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(domain);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			throw new PropertyException("Property has not been saved");
		}
		return domain;
	}

	@Override
	public Property read(Long id) throws Exception {
		return entityManager.find(Property.class, id);
	}

	@Override
	public List<Property> readAll() throws Exception {
		List<Property> listOfProperties = entityManager.createQuery("SELECT p FROM Property p",Property.class).getResultList();
		return listOfProperties;
	}

	@Override
	public Property update(Property domain) throws Exception {
		Property property = listOfProperties.stream().filter(p->p.getPropertyIdNumber().equals(domain.getPropertyIdNumber())).findFirst().orElse(null);
		if(property!=null) {
			property.setOwnerVAT(domain.getOwnerVAT());
			property.setPropertyAddress(domain.getPropertyAddress());
			property.setPropertyType(domain.getPropertyType());
			property.setYearOfConstruction(domain.getYearOfConstruction());
		}
		return property;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		Property p = read(id);
		return listOfProperties.remove(p);
	}

}
