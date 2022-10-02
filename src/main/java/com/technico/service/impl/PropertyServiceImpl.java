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
	public void addProperty(Property property) throws PropertyException {

		Optional<Property> propertyDb = propertyRepository.create(property);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property has not been saved.");
	}

	@Override
	public List<Property> displayAllProperties() {
		return propertyRepository.readAll();
	}

	@Override
	public Property displayProperty(Long id) throws PropertyException {
		return propertyRepository.read(id).orElse(null);
	}

	@Override
	public void updateProperty(Property property) throws PropertyException {
		Optional<Property> propertyDb = propertyRepository.update(property);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property has not been updated.");
	}

	@Override
	public void deleteProperty(Long id) throws PropertyException {
		if (!propertyRepository.delete(id))
			throw new PropertyException("The property could not be deleted.");
	}

//	public Property searchById(String input) throws PropertyException {
//		List<Property> listOfProperties = propertyRepository.readAll();
//		Property property = listOfProperties
//				.stream()
//				.filter(d->d.getPropertyIdNumber().equals(input))
//				.findFirst()
//				.orElse(null);
//		if(property == null)
//			throw new PropertyException("The property with id " +input+ " could not be found.");		
//		return property;	
//	}
//
//	public List<Property> searchByVAT(String input) throws PropertyException {
//		List<Property> listOfProperties = propertyRepository.readAll();
//		List<Property> resultList = listOfProperties
//				.stream()
//				.filter(d->d.getOwner().getOwnerVAT().equals(input))
//				.collect(Collectors.toList());
//		if(resultList.isEmpty())
//			throw new PropertyException("The properties with owner VAT " +input+ " could not be found.");		
//		return resultList;
//	}
	
	public Property searchById(String input) throws PropertyException {
		Optional<Property> propertyDb = propertyRepository.readByIdNumber(input);
		if (propertyDb.isEmpty())
			throw new PropertyException("The property with id " +input+ " could not be found.");
		return propertyDb.get();
	}

	public List<Property> searchByVAT(String input) throws PropertyException {
		List<Property> listOfProperties = propertyRepository.readByOwnerVAT(input);
		if(listOfProperties.isEmpty())
			throw new PropertyException("The properties with owner VAT " +input+ " could not be found.");
		return listOfProperties;
	}

}
