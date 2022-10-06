package com.technico.service;

import java.time.LocalDate;
import java.util.List;

import com.technico.exception.PropertyRepairException;
import com.technico.model.PropertyRepair;

public interface PropertyRepairService {
	
	/**
	 * Add new property repair to database.
	 * @param propertyRepair
	 * @return
	 * @throws PropertyRepairException
	 */
	PropertyRepair addPropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	/**
	 * Read all property repairs from the database.
	 * @return
	 * @throws PropertyRepairException
	 */
	List<PropertyRepair> displayAllPropertyRepairs() throws PropertyRepairException;
	/**
	 * Read a property repair with a specified id from the database.
	 * @param id
	 * @return
	 * @throws PropertyRepairException
	 */
	PropertyRepair displayPropertyRepair(Long id) throws PropertyRepairException;
	/**
	 * Update property repair with specified id to the database.
	 * @param propertyRepair
	 * @return
	 * @throws PropertyRepairException
	 */
	PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException;
	/**
	 * Delete property repair safely with specified id to the database.
	 * @param id
	 * @return
	 * @throws PropertyRepairException
	 */
	boolean deletePropertyRepair(Long id) throws PropertyRepairException;
	/**
	 * Search property repairs between two specified dates.
	 * @param d1
	 * @param d2
	 * @return
	 * @throws PropertyRepairException
	 */
	List<PropertyRepair> searchByDateBetween(LocalDate d1, LocalDate d2) throws PropertyRepairException;
	/**
	 * Search property repairs with a specific owner id.
	 * @param ownerId
	 * @return
	 * @throws PropertyRepairException
	 */
	List<PropertyRepair> searchByOwnerId(Long ownerId) throws PropertyRepairException;

}
