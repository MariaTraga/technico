package com.technico.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.technico.exception.PropertyException;
import com.technico.model.Property;
import com.technico.repository.PropertyRepository;
import com.technico.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
	
	private PropertyRepository propertyRepository;

	public PropertyServiceImpl(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	@Override
	public Property addProperty(Property property) throws PropertyException {

		Optional<Property> propertyDb = propertyRepository.create(property);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property has not been saved.");
		return propertyDb.orElse(null);
	}

	@Override
	public List<Property> readAllProperties() throws PropertyException {
		List<Property> list = propertyRepository.readAll();
		if (list == null || list.isEmpty())
			throw new PropertyException("The list of properties cannot be read.");
		return list;
	}

	@Override
	public Property readProperty(Long id) throws PropertyException {
		Property property = propertyRepository.read(id).orElse(null);
		if(property == null)
			throw new PropertyException("The property cannot be found.");
		return property;
	}

	@Override
	public Property updateProperty(Property property) throws PropertyException {
		Optional<Property> propertyDb = propertyRepository.update(property);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property has not been updated.");
		return propertyDb.orElse(null);
	}

	@Override
	public boolean deleteProperty(Long id) throws PropertyException {
		boolean deleted = propertyRepository.delete(id);
		if (!deleted)
			throw new PropertyException("The property could not be deleted.");
		return deleted;
	}

	@Override
	public Property searchById(String input) throws PropertyException {
		Optional<Property> propertyDb = propertyRepository.readByIdNumber(input);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property with id " + input + " could not be found.");
		return propertyDb.get();
	}

	@Override
	public List<Property> searchByVAT(String input) throws PropertyException {
		List<Property> listOfProperties = propertyRepository.readByOwnerVAT(input);
		if (listOfProperties == null || listOfProperties.isEmpty())
			throw new PropertyException("The properties with owner VAT " + input + " could not be found.");
		return listOfProperties;
	}

	@Override
	public boolean deleteSafeProperty(Long id) throws PropertyException {
		boolean deleted = propertyRepository.deleteSafe(id);
		if (!deleted)
			throw new PropertyException("The property could not be deleted.");
		return deleted;
	}

}
