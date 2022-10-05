package com.technico.repository.impl;

import java.sql.Date;
import java.util.List;

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
	 * public List<PropertyRepair> readByDateBetween(Date d1, Date d2){
	 * List<PropertyRepair> propertyRepairList= entityManager .createQuery("select *
	 * from propertyrepair where repairDate_from >= :repairDate AND repairDate_to <=
	 * :repair .", PropertyRepair.class) .setParameter("")
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
}
