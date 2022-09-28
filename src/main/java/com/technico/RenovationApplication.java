package com.technico;


import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.enums.PropertyType;
import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;
import com.technico.service.PropertyService;
import com.technico.util.JpaUtil;

import jakarta.persistence.EntityManager;

public class RenovationApplication {
	//Branch testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager entityManager = JpaUtil.getEntityManager();
		
		PropertyService propertyService = new PropertyService(entityManager);
		Property property = new Property("123456","Athens","2002",PropertyType.APARTMENT,"111111",2,false);
		
		try {
			propertyService.addProperty(property);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PropertyService ownerService = new PropertyService(entityManager);
		Owner owner = new Owner("EW23464FED","Lalos","Lalakis","Thessaloniki","696954321","lalos@gmail.com","user_lalos53","@53lalos!",false);
		
		try {
			ownerService.addOwner(owner);
		} catch (OwnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(property);
		System.out.println(owner);
		JpaUtil.shutdown();		
		
	}

}
