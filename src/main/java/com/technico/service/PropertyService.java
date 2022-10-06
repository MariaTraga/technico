package com.technico.service;

import java.util.List;

import com.technico.exception.PropertyException;
import com.technico.model.Property;

public interface PropertyService {
	/**
	 * Add new property to database.
	 * @param property
	 * @return
	 * @throws PropertyException
	 */
	Property addProperty(Property property) throws PropertyException;
	/**
	 * Read all properties from the database.
	 * @return
	 * @throws PropertyException
	 */
	List<Property> readAllProperties() throws PropertyException;
	/**
	 * Read a property with a specified id from the database. 
	 * @param id
	 * @return
	 * @throws PropertyException
	 */
	Property readProperty(Long id) throws PropertyException;
	/**
	 * Update property with specified id to the database.
	 * @param property
	 * @return
	 * @throws PropertyException
	 */
	Property updateProperty(Property property) throws PropertyException;
	/**
	 * Delete property permanently with specified id to the database.
	 * @param id
	 * @return
	 * @throws PropertyException
	 */
	boolean deleteProperty(Long id) throws PropertyException;
	/**
	 * Search property with a specific number id.
	 * @param input
	 * @return
	 * @throws PropertyException
	 */
	Property searchById(String input) throws PropertyException;
	/**
	 * Search property with a specific VAT.
	 * @param input
	 * @return
	 * @throws PropertyException
	 */
	List<Property> searchByVAT(String input) throws PropertyException;
	/**
	 * Delete property safely with specified id to the database.
	 * @param id
	 * @return
	 * @throws PropertyException
	 */
	boolean deleteSafeProperty(Long id) throws PropertyException;
}
