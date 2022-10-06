package com.technico.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technico.enums.PropertyType;
import com.technico.enums.RepairType;
import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;
import com.technico.exception.PropertyRepairException;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.model.PropertyRepair;
import com.technico.repository.OwnerRepository;
import com.technico.repository.PropertyRepairRepository;
import com.technico.repository.PropertyRepository;
import com.technico.repository.impl.OwnerRepositoryImpl;
import com.technico.repository.impl.PropertyRepairRepositoryImpl;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.service.OwnerService;
import com.technico.service.PropertyRepairService;
import com.technico.service.PropertyService;

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
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImplTest.class);


	private static Owner owner1, owner2;
	private static Property property1,property2,property3;
	private static PropertyRepair repair1,repair2,repair3;

	
	private static PropertyRepository propertyRepository;
	private static PropertyService propertyService;
	
	private static OwnerRepository ownerRepository;
	private static OwnerService ownerService;
	
	private static PropertyRepairRepository propertyRepairRepository;
	private static PropertyRepairService propertyRepairService;

	@BeforeAll
	static void initStatic() {
		emf = Persistence.createEntityManagerFactory(TEST_UNIT_NAME);
		entityManager = emf.createEntityManager();
		propertyRepository = new PropertyRepositoryImpl(entityManager);
		propertyService = new PropertyServiceImpl(propertyRepository);
		
		ownerRepository = new OwnerRepositoryImpl(entityManager);
		ownerService = new OwnerServiceImpl(ownerRepository);
		
		propertyRepairRepository = new PropertyRepairRepositoryImpl(entityManager);
		propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);
		
		
		try {
			owner1 = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
			owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","john2@mail.com", "john", "1234",false);
			ownerService.addOwner(owner1);
			ownerService.addOwner(owner2);
	
	        // add properties
			property1 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner1,false);
			property2 = new Property("123858","Athens","2003",PropertyType.APARTMENT, owner1,false);
			property3 = new Property("123859","Athens","2003",PropertyType.APARTMENT, owner2,false);
			propertyService.addProperty(property1);
			propertyService.addProperty(property2);
			propertyService.addProperty(property3);
			
	        // add property repairs
			repair1 = new PropertyRepair(new Date(122,9,6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(555), owner1, property1, "Bring plumber", false);
			repair2 = new PropertyRepair(new Date(122,10,6), "Fix apartment", RepairType.INSULATION, new BigDecimal(1455), owner1, property2, "Roof repairs", false);
			repair3 = new PropertyRepair(new Date(122,14,6), "Fix apartment", RepairType.PAINTING, new BigDecimal(70), owner2, property3, "Paint walls", false);
			propertyRepairService.addPropertyRepair(repair1);
			propertyRepairService.addPropertyRepair(repair2);
			propertyRepairService.addPropertyRepair(repair3);
		} catch (OwnerException e) {
        	logger.error("================================>");
        	logger.error("Something went wrong. Details: {}",e.getMessage(),e);
          	logger.error("<================================");
        } catch (PropertyException e) {
        	logger.error("================================>");
            logger.error("Something went wrong. Details: {}",e.getMessage(),e);
            logger.error("<================================");
        } catch (PropertyRepairException e) {
        	logger.error("================================>");
            logger.error("Something went wrong. Details: {}",e.getMessage(),e);
            logger.error("<================================");
        }

	}

	@BeforeEach
	void initialize() {
		
			
	}

	@AfterAll
	static void shutdown() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

	@Test
	@DisplayName("Test database property insertion. This test will fail if duplicate id number.")
	void saveProperty() {
		Long propertyIdNumber = Math.round(Math.floor(Math.random() * (999999 - 100000 + 1) + 100000));
		Property insertedProperty = new Property(propertyIdNumber.toString(), "Athens", "2002", PropertyType.APARTMENT, owner1,
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
		Property insertedProperty = new Property(property1.getPropertyIdNumber(), "Athens", "2002", PropertyType.APARTMENT, owner1,
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
			Property viewedProperty = propertyService.readProperty(property1.getId());
			assertTrue(viewedProperty.getId() > 0, "Property cannot be found.");
		});
	}

	@Test
	@DisplayName("Test incorrect view of a single property.")
	void viewPropertyError() {
		assertAll(() -> {
			assertThrows(PropertyException.class, ()->propertyService.readProperty(0l),
					"Property was found even though primary key id is incorrect.");
		});
	}
	
	@Test
	@DisplayName("Test view of all properties.")
	void viewProperties() {
		assertAll(() -> {
			List<Property> viewedPropertiesList = new ArrayList<>();	
			viewedPropertiesList = propertyService.readAllProperties();
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
			List<Property> searchedPropertiesList = propertyService.searchByVAT(owner2.getOwnerVAT());
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
			Property searchedProperty = propertyService.readProperty(property2.getId());
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
			Property safeDeleteProperty = new Property(propertyIdNumber.toString(),"Athens","2000",PropertyType.MAISONETTE, owner1,false);
			Property savedProperty = propertyService.addProperty(safeDeleteProperty);
			assertTrue(savedProperty.getId() > 0,
					"Property not saved, duplicate property id number or no owner id present.");
			Property searchedProperty = propertyService.readProperty(savedProperty.getId());
			
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
			Property permanentDeleteProperty = new Property(propertyIdNumber.toString(),"Athens","2000",PropertyType.MAISONETTE, owner1,false);
			Property savedProperty = propertyService.addProperty(permanentDeleteProperty);
			assertTrue(savedProperty.getId() > 0,
					"Property not saved, duplicate property id number or no owner id present.");			
			boolean deleted = propertyService.deleteProperty(savedProperty.getId());
			assertTrue(deleted, "Property was not deleted successfully.");
		});
	}

}
