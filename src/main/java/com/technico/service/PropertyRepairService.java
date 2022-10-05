package com.technico.service;

import java.util.List;

import com.technico.exception.PropertyRepairException;
import com.technico.model.PropertyRepair;

public interface PropertyRepairService {
	
	PropertyRepair addPropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	List<PropertyRepair> displayAllPropertyRepairs() throws PropertyRepairException;
	PropertyRepair displayPropertyRepair(Long id) throws PropertyRepairException;
	PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	boolean deletePropertyRepair(Long id) throws PropertyRepairException;

}
