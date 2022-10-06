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

import com.technico.RenovationApplication;
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
public class PropertyRepairServiceImplTest {

	static final String TEST_UNIT_NAME = "Test";

	@PersistenceUnit(unitName = TEST_UNIT_NAME)
	private static EntityManager entityManager;

	@PersistenceUnit(unitName = TEST_UNIT_NAME)
	private static EntityManagerFactory emf;

	private static final Logger logger = LoggerFactory.getLogger(PropertyRepairServiceImplTest.class);

	private static Owner owner1, owner2;
	private static Property property1, property2, property3;
	private static PropertyRepair repair1, repair2, repair3;

	private static PropertyRepository propertyRepository;
	private static PropertyService propertyService;

	private static OwnerRepository ownerRepository;
	private static OwnerService ownerService;

	private static PropertyRepairRepository propertyRepairRepository;
	private static PropertyRepairService propertyRepairService;

	@BeforeAll
	static void initStatic() {
		String testUnitName = "Test";
		emf = Persistence.createEntityManagerFactory(testUnitName);
		entityManager = emf.createEntityManager();
		propertyRepository = new PropertyRepositoryImpl(entityManager);
		propertyService = new PropertyServiceImpl(propertyRepository);

		ownerRepository = new OwnerRepositoryImpl(entityManager);
		ownerService = new OwnerServiceImpl(ownerRepository);

		propertyRepairRepository = new PropertyRepairRepositoryImpl(entityManager);
		propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);

		try {
			owner1 = new Owner("09121212", "John", "Doe", "Athens", "2109999999", "john@mail.com", "john", "1234",
					false);
			owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999", "john2@mail.com", "john", "1234",
					false);
			ownerService.addOwner(owner1);
			ownerService.addOwner(owner2);

			// add properties
			property1 = new Property("123857", "Athens", "2003", PropertyType.APARTMENT, owner1, false);
			property2 = new Property("123858", "Athens", "2003", PropertyType.APARTMENT, owner1, false);
			property3 = new Property("123859", "Athens", "2003", PropertyType.APARTMENT, owner2, false);
			propertyService.addProperty(property1);
			propertyService.addProperty(property2);
			propertyService.addProperty(property3);

			// add property repairs
			repair1 = new PropertyRepair(new Date(122, 9, 6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(555),
					owner1, property1, "Bring plumber", false);
			repair2 = new PropertyRepair(new Date(122, 10, 6), "Fix apartment", RepairType.INSULATION,
					new BigDecimal(1455), owner1, property2, "Roof repairs", false);
			repair3 = new PropertyRepair(new Date(122, 14, 6), "Fix apartment", RepairType.PAINTING, new BigDecimal(70),
					owner2, property3, "Paint walls", false);
			propertyRepairService.addPropertyRepair(repair1);
			propertyRepairService.addPropertyRepair(repair2);
			propertyRepairService.addPropertyRepair(repair3);
		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
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
	void testRepo() {
		assertTrue(propertyRepairRepository != null);
	}

	@Test
	void testService() {
		assertTrue(propertyRepairService != null);
	}

	@Test
	@DisplayName("Test view of a single repair.")
	void viewPropertyRepair() {
		assertAll(() -> {
			PropertyRepair viewedRepair = propertyRepairService.displayPropertyRepair(repair1.getId());
			assertTrue(viewedRepair.getId() > 0, "Repair cannot be found.");
		});

	}

	@Test
	@DisplayName("Test view of all repairs.")
	void viewRepairs() {
		assertAll(() -> {
			List<PropertyRepair> viewedPropertyRepairList = new ArrayList<>();
			viewedPropertyRepairList = propertyRepairService.displayAllPropertyRepairs();
			assertNotEquals(viewedPropertyRepairList, null, "Repair list is null.");
			assertTrue(viewedPropertyRepairList.size() > 0, "Repair list is empty.");
		});
	}

	@Test
	@DisplayName("Test incorrect view of a single repair.")
	void viewPropertyRepairError() {
		assertAll(() -> {
			PropertyRepair viewedRepair = propertyRepairService.displayPropertyRepair(0l);
			assertEquals(viewedRepair, null, "Repair was found even though primary key id is incorrect.");
		});
	}

	@Test
	@DisplayName("Test search repair by owner id number.")
	void searchByIDNumber() {
		assertAll(() -> {
			List<PropertyRepair> searchedrepair = propertyRepairService.searchByOwnerId(owner1.getId());
			assertTrue((searchedrepair).size() > 0, "Repair with owner id number cannot be found.");
		});
	}

	@Test
	@DisplayName("Test incorrect search repair by owner id number.")
	void searchByIDNumberError() {
		assertAll(() -> {
			assertThrows(PropertyRepairException.class, () -> propertyRepairService.searchByOwnerId(100),
					"Property error not thrown, property with incorrect property id number was found."	);
		});
	}
	@Test
	@DisplayName("Test search repair by between two dates.")
	void searchByTwoDates() {
		assertAll(() -> {
			List<PropertyRepair> searchedrepair = propertyRepairService.searchByDateBetween(new Date(122, 9, 6),new Date(122, 10, 6));
			assertTrue((searchedrepair).size() > 0, "Repair with these dates cannot be found.");
		});
}
	@Test
	@DisplayName("Test search repair by between two dates.")
	void searchByTwoDatesError() {
		assertAll(() -> {
			assertThrows(PropertyRepairException.class, () -> propertyRepairService.searchByDateBetween(new Date(122, 10, 6),new Date(122, 9, 6)),
					"Property error not thrown, property with incorrect  repair date was found."	);
		});
}
	
}