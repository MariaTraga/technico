package com.technico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.technico.exception.PropertyException;
import com.technico.model.Property;
import com.technico.repository.impl.PropertyRepositoryImpl;
import com.technico.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	private PropertyRepositoryImpl propertyRepository;

	public PropertyServiceImpl(PropertyRepositoryImpl propertyRepository) {
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
	public List<Property> displayAllProperties() {
		List<Property> list = propertyRepository.readAll();
		return propertyRepository.readAll();
	}

	@Override
	public Property displayProperty(Long id) throws PropertyException {
		return propertyRepository.read(id).orElse(null);
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

	public Property searchById(String input) throws PropertyException {
		Optional<Property> propertyDb = propertyRepository.readByIdNumber(input);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property with id " + input + " could not be found.");
		return propertyDb.get();
	}

	public List<Property> searchByVAT(String input) throws PropertyException {
		List<Property> listOfProperties = propertyRepository.readByOwnerVAT(input);
		if (listOfProperties == null || listOfProperties.isEmpty())
			throw new PropertyException("The properties with owner VAT " + input + " could not be found.");
		return listOfProperties;
	}

	public boolean deleteSafeProperty(Long id) throws PropertyException {
		boolean deleted = propertyRepository.deleteSafe(id);
		if (!deleted)
			throw new PropertyException("The property could not be deleted.");
		return deleted;
	}

}