package com.technico;


import java.math.BigDecimal;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technico.enums.PropertyType;
import com.technico.enums.RepairType;
import com.technico.exception.InvalidEmailException;
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

	public static void main(String[] args) {

		EntityManager entityManager = JpaUtil.getEntityManager();
		
		PropertyRepository propertyRepository = new PropertyRepositoryImpl(entityManager);
		PropertyService propertyService = new PropertyServiceImpl(propertyRepository);

		OwnerRepository ownerRepository = new OwnerRepositoryImpl(entityManager);
		OwnerService ownerService = new OwnerServiceImpl(ownerRepository);

		PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepositoryImpl(entityManager);
		PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);

		//DEMONSTRATION FUNCTIONS
		addDataToDB(ownerService, propertyService, propertyRepairService);
		updatePropertyFromDB(ownerService, 1l);
		//deleteAllOwnersFromDB(ownerService, propertyService, propertyRepairService);
		///////
		JpaUtil.shutdown();
	}

	public static void addDataToDB(OwnerService os, PropertyService ps, PropertyRepairService prs) {
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
			os.addOwner(owner1);
			os.addOwner(owner2);
			os.addOwner(owner3);
			os.addOwner(owner4);
			os.addOwner(owner5);

			// add properties
			Property property1 = new Property("123857", "Athens", "2003", PropertyType.APARTMENT, owner1, false);
			Property property2 = new Property("123858", "Athens", "2003", PropertyType.APARTMENT, owner1, false);
			Property property3 = new Property("123859", "Athens", "2003", PropertyType.APARTMENT, owner2, false);
			ps.addProperty(property1);
			ps.addProperty(property2);
			ps.addProperty(property3);

			// add property repairs
			PropertyRepair repair1 = new PropertyRepair(new Date(122, 9, 6), "Fix apartment", RepairType.PLUMBING,
					new BigDecimal(555), owner1, property1, "Bring plumber", false);
			PropertyRepair repair2 = new PropertyRepair(new Date(122, 10, 6), "Fix apartment", RepairType.INSULATION,
					new BigDecimal(1455), owner1, property2, "Roof repairs", false);
			PropertyRepair repair3 = new PropertyRepair(new Date(122, 14, 6), "Fix apartment", RepairType.PAINTING,
					new BigDecimal(70), owner2, property3, "Paint walls", false);
			prs.addPropertyRepair(repair1);
			prs.addPropertyRepair(repair2);
			prs.addPropertyRepair(repair3);
			
			

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
	
	// retrieve owners
	public static void readAllOwnersFromDB(OwnerService os, PropertyService ps, PropertyRepairService prs) {

		try {
			
			for (Owner owner : os.showAllOwners()) {
				logger.info(owner.toString());
			}

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	// remove all entries through cascade
	public static void deleteAllOwnersFromDB(OwnerService os, PropertyService ps, PropertyRepairService prs) {

		try {		
			for (Owner owner : os.showAllOwners()) {
				logger.info("================================>Deleting owner with id: " + owner.getId());
				os.deleteOwner(owner.getId());
			}

		} catch (OwnerException e) {
			logger.error("================================>");
			logger.error("Something went wrong. Details: {}", e.getMessage(), e);
			logger.error("<================================");
		}
	}

	//update owners
	public static void updatePropertyFromDB(OwnerService os, long id) {
		try {

		Owner owner = os.showOwner(1l);
		logger.info("-=-=-=-=-");
		logger.info("", owner.toString());
		owner.setAddress("Athina");
		owner.setEmail("john2@mail.com");
		owner.setPassword("4231");
		os.updateOwner(owner);
		
		logger.info("Property After Update: {}",os.showOwner(1l).toString());
        logger.info("*************************************");
        
		} catch (OwnerException e) {
			logger.error("================================>");
            logger.error("Something went wrong. Details: {}", e.getMessage(), e);
            logger.error("<================================");
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePropertyFromDB(PropertyService ps,long id) {
        try {



           Property property = ps.displayProperty(1l);
            logger.info("*************************************");
            logger.info("Property Before Update: {}",property.toString());
            property.setPropertyAddress("NEW-ADDRESS");
            property.setPropertyIdNumber("121212");
            property.setPropertyType(PropertyType.DETACHED);
            property.setYearOfConstruction("2021");
            ps.updateProperty(property);
            
            logger.info("Property After Update: {}",ps.displayProperty(1l).toString());
            logger.info("*************************************");



       } catch (PropertyException e) {
            logger.error("================================>");
            logger.error("Something went wrong. Details: {}", e.getMessage(), e);
            logger.error("<================================");
        }
    }
}