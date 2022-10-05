package com.technico.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class JpaUtil {

	private static final String PERSISTENCE_UNIT_NAME = "Persistence";
	@PersistenceUnit(unitName=PERSISTENCE_UNIT_NAME)
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);			
		}
		return factory;
	}
	
	@PersistenceUnit(unitName=PERSISTENCE_UNIT_NAME)
	public static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	
	public static void shutdown() {
		if(factory!=null) {
			factory.close();
			factory = null;
		}
	}

}
