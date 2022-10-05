package com.technico.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.technico.enums.PropertyType;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.repository.impl.PropertyRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {

	static final String TEST_UNIT_NAME = "Test";
	
	@PersistenceUnit(unitName=TEST_UNIT_NAME)
	private static EntityManager entityManager;

	@PersistenceUnit(unitName=TEST_UNIT_NAME)
	private static EntityManagerFactory emf;

	private Owner owner;
	Property property1;
	Property property2;
	
	private static PropertyRepositoryImpl propertyRepository;
	private static PropertyServiceImpl propertyService;

	@BeforeAll
	static void initStatic() {
		String testUnitName = "Test";
		emf = Persistence.createEntityManagerFactory(testUnitName);
		entityManager = emf.createEntityManager();
		propertyRepository = new PropertyRepositoryImpl(entityManager);
		propertyService = new PropertyServiceImpl(propertyRepository);

	}

	@BeforeEach
	void initialize() {	
		owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999", "john@mail.com", "john", "1234", false,null);
		owner.setId(1l);
		property1 = new Property("654321","Athens","2000",PropertyType.MAISONETTE, owner,false);
		property1.setId(1l);
		property2 = new Property("876543","Athens","2000",PropertyType.MAISONETTE, owner,false);
		property2.setId(2l);
			
	}

	@AfterAll
	static void shutdown() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

	@Test
	void testRepo() {
		assertTrue(propertyRepository != null);
	}

	@Test
	void testService() {
		assertTrue(propertyService != null);
	}

	@Test
	@DisplayName("Test database property insertion. This test will fail if duplicate id number.")
	void saveProperty() {
		Long propertyIdNumber = Math.round(Math.floor(Math.random() * (999999 - 100000 + 1) + 100000));
		Property insertedProperty = new Property(propertyIdNumber.toString(), "Athens", "2002", PropertyType.APARTMENT, owner,
				false);
		assertAll(() -> {
			Property savedProperty = propertyService.addProperty(insertedProperty);
			assertTrue(savedProperty.getId() > 0,
					"Property not saved, duplicate property id number or no owner id present.");
		});
	}
	
	@Test
	@DisplayName("Test database incorrect property insertion.")
	void savePropertyError() {
		Property insertedProperty = new Property(property1.getPropertyIdNumber(), "Athens", "2002", PropertyType.APARTMENT, owner,
				false);
		assertAll(() -> {
			assertThrows(PropertyException.class, ()->propertyService.addProperty(insertedProperty),
					"Property error not thrown, property saved incorrectly");
		});
	}

	@Test
	@DisplayName("Test view of a single property.")
	void viewProperty() {
		assertAll(() -> {
			Property viewedProperty = propertyService.displayProperty(property1.getId());
			assertTrue(viewedProperty.getId() > 0, "Property cannot be found.");
		});
	}

	@Test
	@DisplayName("Test incorrect view of a single property.")
	void viewPropertyError() {
		assertAll(() -> {
			Property viewedProperty = propertyService.displayProperty(0l);
			assertEquals(viewedProperty,null, "Property was found even though primary key id is incorrect.");
		});
	}
	
	@Test
	@DisplayName("Test view of all properties.")
	void viewProperties() {
		assertAll(() -> {
			List<Property> viewedPropertiesList = new ArrayList<>();	
			viewedPropertiesList = propertyService.displayAllProperties();
			assertNotEquals(viewedPropertiesList,null, "Property list is null.");
			assertTrue(viewedPropertiesList.size() > 0, "Property list is empty.");
		});	
	}

	@Test
	@DisplayName("Test search property by ID number.")
	void searchByIDNumber() {
		assertAll(() -> {
			Property searchedProperty = propertyService.searchById(property1.getPropertyIdNumber());
			assertTrue(searchedProperty.getId() > 0, "Property with id number cannot be found.");
		});
	}
	
	@Test
	@DisplayName("Test incorrect search property by ID number.")
	void searchByIDNumberError() {
		assertAll(() -> {
			assertThrows(PropertyException.class, ()->propertyService.searchById("XXXXXX"),
					"Property error not thrown, property with incorrect property id number was found.");
		});
	}

	@Test
	@DisplayName("Test search property by owner VAT number.")
	void searchByVATNumber() {
		assertAll(() -> {
			List<Property> searchedPropertiesList = propertyService.searchByVAT(owner.getOwnerVAT());
			assertTrue(searchedPropertiesList.size() > 0, "Properties with owner VAT number cannot be found.");
		});
	}
	
	@Test
	@DisplayName("Test incorrect search property by owner VAT number.")
	void searchByVATNumberError() {
		assertAll(() -> {
			assertThrows(PropertyException.class, ()->propertyService.searchByVAT("XXXXXX"),
					"Property error not thrown, property with incorrect property id number was found.");
		});
	}

	@Test
	@DisplayName("Test update function.")
	void updateProperty() {
		assertAll(() -> {
			Property searchedProperty = propertyService.searchById(property1.getPropertyIdNumber());
			searchedProperty.setPropertyAddress("NEW-ADDRESS "+Math.round(Math.floor(Math.random() * (10))));
			searchedProperty.setPropertyType(PropertyType.DETACHED);
			Property updatedProperty = propertyService.updateProperty(searchedProperty);
			assertEquals(updatedProperty.getId(), searchedProperty.getId(), "Property ids do not match, search failed.");
			assertEquals(updatedProperty.getPropertyAddress(), searchedProperty.getPropertyAddress(),
					"Updated properties do not match, update failed.");
		});
	}
	
	@Test
	//@Disabled
	@DisplayName("Test incorrect update function.")
	void updatePropertyError() {
		assertAll(() -> {
			Property searchedProperty = propertyService.displayProperty(property2.getId());
			searchedProperty.setPropertyIdNumber(property1.getPropertyIdNumber());
			assertThrows(PropertyException.class, ()->propertyService.updateProperty(searchedProperty),
					"Property error not thrown, property was updated with duplicate property Id number.");
			});	
	}

	@Test
	//@Disabled
	@DisplayName("Test safe delete function.")
	void deleteSafeProperty() {
		assertAll(() -> {
			Long propertyIdNumber = Math.round(Math.floor(Math.random() * (999999 - 100000 + 1) + 100000));
			Property safeDeleteProperty = new Property(propertyIdNumber.toString(),"Athens","2000",PropertyType.MAISONETTE, owner,false);
			Property savedProperty = propertyService.addProperty(safeDeleteProperty);
			assertTrue(savedProperty.getId() > 0,
					"Property not saved, duplicate property id number or no owner id present.");
			Property searchedProperty = propertyService.displayProperty(savedProperty.getId());
			
			boolean deleted = propertyService.deleteSafeProperty(searchedProperty.getId());
			assertTrue(deleted, "Property was not deleted successfully.");
		});
	}

	@Test
	//@Disabled
	@DisplayName("Test database permanent delete function.")
	void deleteProperty() {
		assertAll(() -> {
			Long propertyIdNumber = Math.round(Math.floor(Math.random() * (999999 - 100000 + 1) + 100000));
			Property permanentDeleteProperty = new Property(propertyIdNumber.toString(),"Athens","2000",PropertyType.MAISONETTE, owner,false);
			Property savedProperty = propertyService.addProperty(permanentDeleteProperty);
			assertTrue(savedProperty.getId() > 0,
					"Property not saved, duplicate property id number or no owner id present.");			
			boolean deleted = propertyService.deleteProperty(savedProperty.getId());
			assertTrue(deleted, "Property was not deleted successfully.");
		});
	}

}
