package com.technico.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.technico.enums.PropertyType;
import com.technico.enums.RepairType;
import com.technico.model.Owner;
import com.technico.model.Property;
import com.technico.model.PropertyRepair;

public class GeneralUtility {
	
	public static List<Owner> createOwnerDataDemonstration() {
		
		Owner owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false,null);
		Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","johnie@mail.com", "john", "1234",false,null);
		Owner owner3 = new Owner("09121214", "Jane", "Doe", "Athens", "2109999999","jane@mail.com", "jane", "1234",false,null);
		
		List<Owner> ownersList = new ArrayList<>();
		ownersList.add(owner);
		ownersList.add(owner2);
		ownersList.add(owner3);
		
		return ownersList;
	}
	
	public static List<Property> createPropertyDataDemonstration() {
		
		Owner owner = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false,null);
		owner.setId(1l);
		Owner owner2 = new Owner("09121213", "John", "Doe", "Athens", "2109999999","johnie@mail.com", "john", "1234",false,null);
		owner2.setId(2l);
		
		Property property = new Property("123456","Athens","2002",PropertyType.APARTMENT, owner,false);
		Property property2 = new Property("123457","Athens","2002",PropertyType.APARTMENT, owner2,false);
		Property property3 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner2,false);
		
		List<Property> propertiesList = new ArrayList<>();
		propertiesList.add(property);
		propertiesList.add(property2);
		propertiesList.add(property3);
		
		return propertiesList;
	}
	
	public static List<PropertyRepair> createPropertyRepairDataDemonstration(){
		
		Owner owner1 = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false,null);
		Owner owner2 = new Owner("09121212", "John", "Doe", "Athens", "2109999999","john@mail.com", "john", "1234",false,null);
		
		Property property1 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner1,false);
		Property property2 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner1,false);
		Property property3 = new Property("123857","Athens","2003",PropertyType.APARTMENT, owner2,false);
				
		PropertyRepair repair1 = new PropertyRepair(new Date(122,9,6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(455), owner1, property1, "Bring plumber", false);
		PropertyRepair repair2 = new PropertyRepair(new Date(122,10,6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(455), owner1, property1, "Bring plumber", false);
		PropertyRepair repair3 = new PropertyRepair(new Date(122,14,6), "Fix apartment", RepairType.PLUMBING, new BigDecimal(455), owner1, property1, "Bring plumber", false);
		
		List<PropertyRepair> propertyRepairList = new ArrayList<>();
		
		
		propertyRepairList.add(repair1);
		propertyRepairList.add(repair2);
		propertyRepairList.add(repair3);
		
		
		return propertyRepairList;
	}

}
