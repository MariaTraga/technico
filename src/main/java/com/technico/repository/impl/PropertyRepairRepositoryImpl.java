package com.technico.repository.impl;

import java.sql.Date;
import java.util.List;

import com.technico.model.Property;
import com.technico.model.PropertyRepair;
import com.technico.repository.PropertyRepairRepository;

import jakarta.persistence.EntityManager;

public class PropertyRepairRepositoryImpl extends DBRepositoryImpl<PropertyRepair, Long>
		implements PropertyRepairRepository {
	EntityManager entityManager;

	public PropertyRepairRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public String getEntityClassName() {
		return PropertyRepair.class.getName();
	}

	@Override
	public Class<PropertyRepair> getEntityClass() {
		return PropertyRepair.class;
	}

	
	
	
	  
	 
	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public List<PropertyRepair> readByDateBetween(Date d1, Date d2) {
		List<PropertyRepair> propertyRepairList = entityManager
				.createQuery(
						"select r from " + getEntityClassName()
								+ " r where r.repairDate BETWEEN :repairDate_from AND :repairDate_to ",
						PropertyRepair.class)
				.setParameter("repairDate_from", d1).setParameter("repairDate_to", d2).getResultList();
		return propertyRepairList;

	}
	
	public List<PropertyRepair> readByOwnerId(long ownerId) {
		List<PropertyRepair> propertyRepairList = entityManager
				.createQuery("SELECT r from " + getEntityClassName() + " r WHERE r.owner.ownerId = :ownerId",
						PropertyRepair.class)
				.setParameter("ownerId", ownerId).getResultList();

		return propertyRepairList;
	}
}
