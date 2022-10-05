package com.technico;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technico.enums.PropertyType;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.service.impl.PropertyServiceImpl;
import com.technico.util.GeneralUtility;
import com.technico.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class RenovationApplication {

	private static final Logger logger = LoggerFactory.getLogger(RenovationApplication.class);
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		PropertyRepositoryImpl propertyRepository = new PropertyRepositoryImpl(entityManager);
		PropertyServiceImpl propertyService = new PropertyServiceImpl(propertyRepository);
		
		Owner owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
		owner.setId(1l);
		Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","johnie@mail.com", "john", "1234",false);
		owner2.setId(2l);
		Property property = new Property("123456","Athens","2002",PropertyType.APARTMENT, owner,false);
		Property property2 = new Property("123457","Athens","2002",PropertyType.APARTMENT, owner2,false);
		Property property3 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner2,false);
		
		try {
			for(Property p : GeneralUtility.createPropertyDataDemonstration()) {
				propertyService.addProperty(p);
			}
		} catch (Exception e) {
			logger.error("Something went wrong. Details: {}",e.getMessage(),e);
			//e.printStackTrace();
		}
		
		try {
			Property searchedProperty = propertyService.searchByVAT(owner.getOwnerVAT()).stream().findFirst().orElse(null);
			logger.info("Searched property by owner VAT was found, first entry has id: {} -- property id number: {}"
					,searchedProperty.getId()
					,searchedProperty.getPropertyIdNumber());
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			propertyService.deleteProperty(property3.getId());
//			//System.out.println("Hello! "+propertyService.displayProperty(9l));
//		} catch (PropertyException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		
		JpaUtil.shutdown();		 

	}

}
