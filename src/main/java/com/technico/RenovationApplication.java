package com.technico;


import com.technico.enums.PropertyType;
import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.repository.OwnerRepository;
import com.technico.repository.impl.OwnerRepositoryImpl;
import com.technico.service.OwnerService;
import com.technico.service.PropertyService;
import com.technico.service.impl.OwnerServiceImpl;
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
		

		Owner owner = new Owner("WOOF1234", "Jack", "Papajackopoulos", "Thessaloniki", "693425354", "jack@gmail.com", "jackson33", "1234", false);
		Owner owner1 = new Owner("ASDASD123", "Rick", "Rickpoulos", "Athina", "23423523", "Rick@gmail.com", "Rickson66", "4321", false);
		Owner owner2 = new Owner("XNZ66RT69", "Fror", "Froreridis", "Patra", "694523452", "Fror@gmail.com", "Frorson66", "2314", false);

		//Branch testing
		System.out.println(property);

		JpaUtil.shutdown();
	}
}