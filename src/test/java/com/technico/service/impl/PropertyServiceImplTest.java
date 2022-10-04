package com.technico.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.technico.enums.PropertyType;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.util.JpaUtil;

import jakarta.persistence.EntityManager;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {
	
	private EntityManager entityManager;
	
	static Long propertyIdNumber;
	
	private Owner owner;
	private PropertyRepositoryImpl propertyRepository;
	private PropertyServiceImpl propertyService;
	
	@BeforeAll
	static void initStatic() {
		propertyIdNumber = Math.round(Math.floor(Math.random()*(999999-100000+1)+100000));
	}
	
	
	@BeforeEach
	void initialize() {
		
		entityManager = JpaUtil.getEntityManager();
		//entityManager.getTransaction().begin();

		propertyRepository = new PropertyRepositoryImpl(entityManager);
		propertyService = new PropertyServiceImpl(propertyRepository);
		owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
		owner.setId(1l);
	}
	
	@AfterEach
    public void rollbackTransaction() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
        }
		JpaUtil.shutdown();	
    }
	
	@Test
	void testRepo() {
		assertTrue(propertyRepository!=null);
	}
	
	@Test
	void testService() {
		assertTrue(propertyService!=null);
	}
	
	@Test
	@Order(1)
	@DisplayName("Test database property insertion. This test will fail if duplicate id number.")
	void saveProperty() {
		System.out.println("ONE");
		Property property = new Property(propertyIdNumber.toString(),"Athens","2002",PropertyType.APARTMENT, owner,false);
		Property savedProperty = new Property();
		try {
			savedProperty = propertyService.addProperty(property);
		} catch (PropertyException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}			
		assertTrue(savedProperty.getId()>0,"Property not saved, duplicate property id number or no owner id present.");
	}
	
	@Test
	@DisplayName("Test database, view a single property.")
	void viewProperty() {
		Property viewedProperty = new Property();
		try {
			viewedProperty = propertyService.displayProperty(1l);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(viewedProperty.getId()>0,"Property cannot be found.");
	}
	
	@Test
	@DisplayName("Test database, view all properties.")
	void viewProperties() {
		List<Property> viewedPropertiesList = new ArrayList<>();

		viewedPropertiesList = propertyService.displayAllProperties();

		assertTrue(viewedPropertiesList.size()>0,"Property list is empty.");
	}
	
	@Test
	@Order(3)
	@DisplayName("Test database, search property by ID number.")
	void searchByIDNumber() {
		System.out.println("THREE");
		try {
			Property searchedProperty = propertyService.searchById(propertyIdNumber.toString());
			assertTrue(searchedProperty.getId()>0,"Property with id number cannot be found.");
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	@Order(2)
	@DisplayName("Test database update function.")
	void updateProperty() {
		System.out.println("TWO");
		try {
			Property searchedProperty = propertyService.searchById(propertyIdNumber.toString());
			searchedProperty.setPropertyAddress("TEST-ADDRESS");
			Property updatedProperty = propertyService.updateProperty(searchedProperty);
			assertAll(
					()-> assertEquals(updatedProperty.getId(),searchedProperty.getId(), "Property ids do not match."),
					()-> assertEquals(updatedProperty.getPropertyAddress(),searchedProperty.getPropertyAddress(),"Updated properties do not match, update failed.")
			);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("Test database delete function.")
	void deleteProperty() {
		System.out.println("FOUR");
		try {
			Property searchedProperty = propertyService.searchById(propertyIdNumber.toString());
			boolean deleted = propertyService.deleteProperty(searchedProperty.getId());
			assertTrue(deleted,"Property was not deleted successfully.");
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
