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
		Owner owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
		owner.setId(1l);
		Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","johnie@mail.com", "john", "1234",false);
		owner2.setId(2l);
		
		Property property = new Property();
		Property property2 = new Property();
		Property property3 = new Property();
		
		try {
			//propertyService.addProperty(property);
			//propertyService.addProperty(property2);
			propertyService.addProperty(property3);
		} catch (PropertyException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		
		
		JpaUtil.shutdown();		 

	}

}
