package com.technico.service;

import java.util.List;

import com.technico.exception.PropertyException;
import com.technico.model.Property;

public interface PropertyService {
	Property addProperty(Property property) throws PropertyException;
	List<Property> readAllProperties() throws PropertyException;
	Property readProperty(Long id) throws PropertyException;
	Property updateProperty(Property property) throws PropertyException;
	boolean deleteProperty(Long id) throws PropertyException;
	Property searchById(String input) throws PropertyException;
	List<Property> searchByVAT(String input) throws PropertyException;
	boolean deleteSafeProperty(Long id) throws PropertyException;
}
