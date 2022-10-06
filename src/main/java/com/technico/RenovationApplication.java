package com.technico;



import java.math.BigDecimal;
import java.sql.Date;

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
import com.technico.repository.impl.OwnerRepositoryImpl;
import com.technico.repository.impl.PropertyRepairRepositoryImpl;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.service.impl.OwnerServiceImpl;
import com.technico.service.impl.PropertyRepairServiceImpl;
import com.technico.service.impl.PropertyServiceImpl;
import com.technico.util.GeneralUtility;



import com.technico.util.JpaUtil;
import jakarta.persistence.EntityManager;



public class RenovationApplication {

   private static final Logger logger = LoggerFactory.getLogger(RenovationApplication.class);
    
    public static void main(String[] args) {
        
        EntityManager entityManager = JpaUtil.getEntityManager();
        
        PropertyRepositoryImpl propertyRepository = new PropertyRepositoryImpl(entityManager);
        PropertyServiceImpl propertyService = new PropertyServiceImpl(propertyRepository);
        
        OwnerRepositoryImpl ownerRepository = new OwnerRepositoryImpl(entityManager);
        OwnerServiceImpl ownerService = new OwnerServiceImpl(ownerRepository);
        
        PropertyRepairRepositoryImpl propertyRepairRepository = new PropertyRepairRepositoryImpl(entityManager);
        PropertyRepairServiceImpl propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);
        
        try {
	        // add owners
			Owner owner1 = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false);
			Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","john2@mail.com", "john", "1234",false);
			ownerService.addOwner(owner1);
			ownerService.addOwner(owner2);
	
	        // add properties
			Property property1 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner1,false);
			Property property2 = new Property("123858","Athens","2003",PropertyType.APARTMENT, owner1,false);
			Property property3 = new Property("123859","Athens","2003",PropertyType.APARTMENT, owner2,false);
			propertyService.addProperty(property1);
			propertyService.addProperty(property2);
			propertyService.addProperty(property3);
			
	        // add property repairs
			PropertyRepair repair1 = new PropertyRepair(new Date(122,9,6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(555), owner1, property1, "Bring plumber", false);
			PropertyRepair repair2 = new PropertyRepair(new Date(122,10,6), "Fix apartment", RepairType.INSULATION, new BigDecimal(1455), owner1, property2, "Roof repairs", false);
			PropertyRepair repair3 = new PropertyRepair(new Date(122,14,6), "Fix apartment", RepairType.PAINTING, new BigDecimal(70), owner2, property3, "Paint walls", false);
			propertyRepairService.addPropertyRepair(repair1);
			propertyRepairService.addPropertyRepair(repair2);
			propertyRepairService.addPropertyRepair(repair3);
			
			// retrieve owners
			logger.info(ownerService.readAllOwners().toString());
			
			// remove all entries through cascade
			for(Owner owner : ownerService.readAllOwners()) {
				logger.error("================================>Deleting owner with id: " + owner.getId());
				ownerService.deleteOwner(owner.getId());
			}
		
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
        
//        try {
//            for(Property p : GeneralUtility.createPropertyDataDemonstration()) {
//                propertyService.addProperty(p);
//            }
//        } catch (Exception e) {
//        	logger.error("================================>");
//            logger.error("Something went wrong. Details: {}",e.getMessage(),e);
//            logger.error("<================================");
//            //e.printStackTrace();
//        }
//        
//        try {
//            Property searchedProperty = propertyService.searchByVAT(owner.getOwnerVAT()).stream().findFirst().orElse(null);
//            logger.info("Searched property by owner VAT was found, first entry has id: {} -- property id number: {}"
//                    ,searchedProperty.getId()
//                    ,searchedProperty.getPropertyIdNumber());
//
//
//
//       } catch (PropertyException e) {
//            System.out.println(e.getMessage());
//            //e.printStackTrace();
//        }
        
//        Owner owner = new Owner("WOOF1234", "Jack", "Papajackopoulos", "Thessaloniki", "693425354", "jack@gmail.com", "jackson33", "1234", false);
//        Owner owner1 = new Owner("ASDASD123", "Rick", "Rickpoulos", "Athina", "23423523", "Rick@gmail.com", "Rickson66", "4321", false);
//        Owner owner2 = new Owner("XNZ66RT69", "Fror", "Froreridis", "Patra", "694523452", "Fror@gmail.com", "Frorson66", "2314", false);
//        try {
//            propertyService.deleteProperty(property3.getId());
//            //System.out.println("Hello! "+propertyService.displayProperty(9l));
//        } catch (PropertyException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

       JpaUtil.shutdown();
    }

}