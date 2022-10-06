package com.technico.service;

import java.sql.Date;
import java.util.List;

import com.technico.exception.PropertyRepairException;
import com.technico.model.Owner;
import com.technico.model.PropertyRepair;

public interface PropertyRepairService {
	
	PropertyRepair addPropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	List<PropertyRepair> displayAllPropertyRepairs() throws PropertyRepairException;
	PropertyRepair displayPropertyRepair(Long id) throws PropertyRepairException;
	PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	boolean deletePropertyRepair(Long id) throws PropertyRepairException;
	List<PropertyRepair> searchByDateBetween(Date d1, Date d2) throws PropertyRepairException;
	List<PropertyRepair> searchByOwnerId(long ownerId) throws PropertyRepairException;

}
