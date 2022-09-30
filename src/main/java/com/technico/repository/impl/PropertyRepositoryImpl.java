package com.technico.repository.impl;

import com.technico.model.Property;
import com.technico.repository.PropertyRepository;
import jakarta.persistence.EntityManager;

public class PropertyRepositoryImpl extends DBRepositoryImpl<Property,Long> implements PropertyRepository  {

	//TODO Refactor exceptions to custom exceptions

	public PropertyRepositoryImpl(EntityManager entityManager) {
		 super(entityManager);
	 }

	@Override
	public String getEntityClassName() {
		return Property.class.getName();
	}

	@Override
	public Class<Property> getEntityClass() {
		return Property.class;
	}


}
