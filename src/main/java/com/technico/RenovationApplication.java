package com.technico;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
import com.technico.service.impl.OwnerServiceImpl;
import com.technico.service.impl.PropertyRepairServiceImpl;
import com.technico.service.impl.PropertyServiceImpl;
import com.technico.util.JpaUtil;

import jakarta.persistence.EntityManager;

public class RenovationApplication {

	private static final Logger logger = LoggerFactory.getLogger(RenovationApplication.class);

	private static final EntityManager entityManager = JpaUtil.getEntityManager();

	private static final PropertyRepository propertyRepository = new PropertyRepositoryImpl(entityManager);
	private static final PropertyService propertyService = new PropertyServiceImpl(propertyRepository);

	private static final OwnerRepository ownerRepository = new OwnerRepositoryImpl(entityManager);
	private static final OwnerService ownerService = new OwnerServiceImpl(ownerRepository);

	private static final PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepositoryImpl(
			entityManager);
	private static final PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(
			propertyRepairRepository);

	public static void main(String[] args) {

		// DEMONSTRATION FUNCTIONS
		addDataToDB();

		// OWNER
		readAllOwnersFromDB();
		updateOwnerFromDB(1l);
		// updateOwnerFromDBException(1l);
		searchOwnerByVAT("WOOF1234");
		searchOwnerByEmail("Fror@gmail.com");
		//deleteAllOwnersFromDB();

		// PROPERTY
		readAllPropertiesFromDB();
		readPropertyFromDB(2l);
		updatePropertyFromDB(1l);
		searchPropertyByIDNumber("123858");
		searchPropertyByVAT("09121212");
		//deleteAllPropertiesFromDBSafe();
		//deleteAllPropertiesFromDBPermanent();

		// REPAIR
		searchRepairByOwnerIDNumber(1l);
		searchRepairBetweenDates(LocalDate.of(2022, 10, 6), LocalDate.of(2022, 12, 6));
		updateRepairFromDB(2l);
		//deleteRepairFromDB(4l);

		// END
		JpaUtil.shutdown();
	}

	public static void addDataToDB() {
		try {
			// add owners
			Owner owner1 = new Owner("09121212", "John", "Doe", "Athens", "2109999999", "john@mail.com", "john", "1234",
					false);
			Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999", "john2@mail.com", "john",
					"1234", false);
			Owner owner3 = new Owner("ASDASD123", "Rick", "Rickpoulos", "Athina", "23423523", "Rick@gmail.com",
					"Rickson66", "4321", false);
			Owner owner4 = new Owner("XNZ66RT69", "Fror", "Froreridis", "Patra", "694523452", "Fror@gmail.com",
					"Frorson66", "2314", false);
			Owner owner5 = new Owner("WOOF1234", "Jack", "Papajackopoulos", "Thessaloniki", "693425354",
					"jack@gmail.com", "jackson33", "1234", false);
			ownerService.addOwner(owner1);
			ownerService.addOwner(owner2);
			ownerService.addOwner(owner3);
			ownerService.addOwner(owner4);
			ownerService.addOwner(owner5);

			// add properties
			Property property1 = new Property("123857", "Athens", "2003", PropertyType.APARTMENT, owner1, false);
			Property property2 = new Property("123858", "Athens", "2008", PropertyType.MAISONETTE, owner1, false);
			Property property3 = new Property("123859", "Kavala", "2012", PropertyType.DETACHED, owner2, false);
			Property property4 = new Property("123860", "Patra", "2020", PropertyType.MAISONETTE, owner3, false);
			Property property5 = new Property("123862", "Thessaloniki", "2005", PropertyType.APARTMENT, owner4, false);
			propertyService.addProperty(property1);
			propertyService.addProperty(property2);
			propertyService.addProperty(property3);
			propertyService.addProperty(property4);
			propertyService.addProperty(property5);

			// add property repairs
			PropertyRepair repair1 = new PropertyRepair(LocalDate.of(2022, 9, 6), "Fix apartment", RepairType.PLUMBING,
					new BigDecimal(555), owner1, property1, "Bring plumber", false);
			PropertyRepair repair2 = new PropertyRepair(LocalDate.of(2022, 10, 6), "Fix apartment",
					RepairType.INSULATION, new BigDecimal(1455), owner1, property2, "Roof repairs", false);
			PropertyRepair repair3 = new PropertyRepair(LocalDate.of(2022, 12, 6), "Fix apartment", RepairType.PAINTING,
					new BigDecimal(70), owner2, property3, "Paint walls", false);
			PropertyRepair repair4 = new PropertyRepair(LocalDate.of(2022, 12, 6), "Fix apartment", RepairType.FRAMES,
					new BigDecimal(155), owner3, property3, "Paint walls", false);

			propertyRepairService.addPropertyRepair(repair1);
			propertyRepairService.addPropertyRepair(repair2);
			propertyRepairService.addPropertyRepair(repair3);
			propertyRepairService.addPropertyRepair(repair4);

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

	// OWNERS

	public static void readAllOwnersFromDB() {

		try {
			for (Owner owner : ownerService.showAllOwners()) {
				logger.info(owner.toString());
			}

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void deleteAllOwnersFromDB() {

		try {
			for (Owner owner : ownerService.showAllOwners()) {
				logger.info("================================>Deleting owner with id: " + owner.getId());
				ownerService.deleteOwner(owner.getId());
			}

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void updateOwnerFromDB(long id) {
		try {

			Owner owner = ownerService.showOwner(1l);
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			logger.info("Owner Before Update: {}", owner.toString());
			owner.setAddress("NEW-ADDRESS");
			owner.setEmail("john@mail.com");
			owner.setPassword("4231");
			ownerService.updateOwner(owner);

			logger.info("Owner After Update: {}", ownerService.showOwner(1l).toString());
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void updateOwnerFromDBException(long id) {
		try {

			Owner owner = ownerService.showOwner(1l);
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			logger.info("Owner Before Update: {}", owner.toString());
			owner.setAddress("Athina");
			owner.setEmail("john2@mail.com");
			owner.setPassword("4231");
			ownerService.updateOwner(owner);

			logger.info("Owner After Update: {}", ownerService.showOwner(1l).toString());
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void searchOwnerByVAT(String vat) {
		try {
			Owner owner = ownerService.searchVAT(vat);
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			logger.info("Owner Found: {}", owner.toString());
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void searchOwnerByEmail(String email) {
		try {
			Owner owner = ownerService.searchEmail(email);
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			logger.info("Owner Found: {}", owner.toString());
			logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	// PROPERTY

	public static void readAllPropertiesFromDB() {

		try {

			for (Property property : propertyService.readAllProperties()) {
				logger.info(property.toString());
			}

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void deleteAllPropertiesFromDBPermanent() {

		try {
			for (Property property : propertyService.readAllProperties()) {
				logger.info("================================> Deleting property with id: " + property.getId());
				propertyService.deleteProperty(property.getId());
			}

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void deleteAllPropertiesFromDBSafe() {

		try {
			for (Property property : propertyService.readAllProperties()) {
				logger.info("================================> Deleting property with id: " + property.getId());
				propertyService.deleteSafeProperty(property.getId());
			}

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void readPropertyFromDB(long id) {

		try {
			logger.info("*************************************");
			logger.info("Property Found: {}", propertyService.readProperty(id).toString());
			logger.info("*************************************");
		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	public static void updatePropertyFromDB(long id) {
		try {

			Property property = propertyService.readProperty(id);
			logger.info("*************************************");
			logger.info("Property Before Update: {}", property.toString());
			property.setPropertyAddress("NEW-ADDRESS");
			// property.setPropertyIdNumber("123858");
			property.setPropertyIdNumber("121212");
			property.setPropertyType(PropertyType.DETACHED);
			property.setYearOfConstruction("2021");
			propertyService.updateProperty(property);

			logger.info("Property After Update: {}", propertyService.readProperty(id).toString());
			logger.info("*************************************");

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void searchPropertyByIDNumber(String id) {

		try {
			Property property = propertyService.searchById(id);
			logger.info("*************************************");
			logger.info("Property With Number Id Found: {}", property.toString());
			logger.info("*************************************");

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	public static void searchPropertyByVAT(String vat) {

		try {
			List<Property> propertyList = propertyService.searchByVAT(vat);
			logger.info("*************************************");
			for (Property property : propertyList) {
				logger.info("Property With Owner VAT Found: {}", property.toString());
			}
			logger.info("*************************************");

		} catch (PropertyException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	// PROPERTY REPAIR

	public static void readAllRepairsFromDB() {

		try {
			List<PropertyRepair> propertyRepairList = propertyRepairService.displayAllPropertyRepairs();
			logger.info("+++++++++++++++++++++++++++++++++++");
			for (PropertyRepair propertyRepair : propertyRepairList) {
				logger.info("Property Repair Found: {}", propertyRepair.toString());
			}
			logger.info("+++++++++++++++++++++++++++++++++++");

		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	public static void searchRepairBetweenDates(LocalDate date1, LocalDate date2) {

		try {
			List<PropertyRepair> propertyRepairList = propertyRepairService.searchByDateBetween(date1, date2);
			logger.info("+++++++++++++++++++++++++++++++++++");
			for (PropertyRepair propertyRepair : propertyRepairList) {
				logger.info("Property Repair Between Dates Found: {}", propertyRepair.toString());
			}
			logger.info("+++++++++++++++++++++++++++++++++++");

		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	public static void searchRepairByOwnerIDNumber(long id) {

		try {
			List<PropertyRepair> propertyRepairList = propertyRepairService.searchByOwnerId(id);
			logger.info("+++++++++++++++++++++++++++++++++++");
			for (PropertyRepair propertyRepair : propertyRepairList) {
				logger.info("Property Repair With Owner Id Found: {}", propertyRepair.toString());
			}
			logger.info("+++++++++++++++++++++++++++++++++++");

		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}

	}

	public static void updateRepairFromDB(long id) {
		try {

			PropertyRepair propertyRepair = propertyRepairService.displayPropertyRepair(id);
			logger.info("+++++++++++++++++++++++++++++++++++");
			logger.info("Property Repair Before Update: {}", propertyRepair.toString());
			propertyRepair.setShortDescription("NEW-SHORT-DESCRIPTION");
			propertyRepair.setDescription("NEW-DESCRIPTION");
			propertyRepair.setRepairCost(new BigDecimal(999));
			propertyRepairService.updatePropertyRepair(propertyRepair);

			logger.info("Property Repair After Update: {}", propertyRepairService.displayPropertyRepair(id).toString());
			logger.info("+++++++++++++++++++++++++++++++++++");

		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	public static void deleteRepairFromDB(long id) {
		try {
			PropertyRepair propertyRepair = propertyRepairService.displayPropertyRepair(id);
			logger.info("================================>Deleting property repair with id: " + propertyRepair.getId());
			propertyRepairService.deletePropertyRepair(id);

		} catch (PropertyRepairException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}
}