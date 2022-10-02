package com.technico;


import com.technico.enums.PropertyType;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.repository.PropertyRepository;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.service.impl.PropertyServiceImpl;
import com.technico.util.JpaUtil;

import jakarta.persistence.EntityManager;

public class RenovationApplication {
	//Branch testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManager entityManager = JpaUtil.getEntityManager();
		
		PropertyRepositoryImpl propertyRepository = new PropertyRepositoryImpl(entityManager);
		PropertyServiceImpl propertyService = new PropertyServiceImpl(propertyRepository);
		
		Owner owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
		owner.setId(1l);
		Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","johnie@mail.com", "john", "1234",false);
		owner2.setId(2l);
		Property property = new Property("123456","Athens","2002",PropertyType.APARTMENT, owner,false);
		Property property2 = new Property("123457","Athens","2002",PropertyType.APARTMENT, owner2,false);
		
		try {
			propertyService.addProperty(property);
			propertyService.addProperty(property2);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(property);
		
		try {
			System.out.println("Hey hey! "+propertyService.searchByVAT(owner.getOwnerVAT()).stream().findFirst());
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//propertyService.deleteProperty(property2.getId());
			System.out.println("Hello! "+propertyService.displayProperty(9l));
		} catch (PropertyException e) {
			System.out.println(e.getMessage());
		}
		
		JpaUtil.shutdown();		 

	}

}
