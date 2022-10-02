package com.technico.service;

import java.util.List;

import com.technico.exception.PropertyException;
import com.technico.model.Property;


//TODO Make an interface
public interface PropertyService {

	void addProperty(Property property) throws PropertyException;
	List<Property> displayAllProperties() throws PropertyException;
	Property displayProperty(Long id) throws PropertyException;
	void updateProperty(Property property) throws PropertyException;
	void deleteProperty(Long id) throws PropertyException;
	
}
