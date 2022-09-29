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
		

		//Branch testing
		System.out.println(property);
		
		JpaUtil.shutdown();		 

	}

}
