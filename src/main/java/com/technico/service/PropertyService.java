package com.technico.service;

import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;

import jakarta.persistence.EntityManager;

//TODO Make an interface
public class PropertyService {

	private EntityManager entityManager;

	public PropertyService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * public Property searchById(String input) throws Exception { return
	 * propertyRepository.read(input); }
	 * 
	 * public Property searchByVAT(String input) throws Exception { Property
	 * property =
	 * propertyRepository.readAll().stream().filter(p->p.getOwnerVAT().equals(input)
	 * ).findFirst().orElse(null); return property; }
	 */

	public void addProperty(Property property) throws PropertyException {

		// TODO To be moved to repository layer
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(property);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new PropertyException("Property has not been saved");
		}
	}

	/*
	 * public List<Property> displayProperty(){
	 * 
	 * }
	 */
	public void addOwner(Owner owner) throws OwnerException {

		// TODO To be moved to repository layer
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(owner);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new OwnerException("Owner has not been saved");
		}
	}
}
