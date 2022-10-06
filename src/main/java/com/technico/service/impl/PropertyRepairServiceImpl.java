package com.technico.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.technico.exception.PropertyRepairException;
import com.technico.model.PropertyRepair;
import com.technico.repository.PropertyRepairRepository;
import com.technico.service.PropertyRepairService;

public class PropertyRepairServiceImpl implements PropertyRepairService {
	private PropertyRepairRepository propertyRepairRepository;

	public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
		this.propertyRepairRepository = propertyRepairRepository;
	}

	@Override
	public PropertyRepair addPropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException {

		Optional<PropertyRepair> propertyRepairDb = propertyRepairRepository.create(propertyRepair);
		if (propertyRepairDb.isEmpty())
			throw new PropertyRepairException("The repair has not been saved.");
		return propertyRepairDb.orElse(null);
	}

	@Override
	public List<PropertyRepair> displayAllPropertyRepairs() throws PropertyRepairException {
		List<PropertyRepair> list = propertyRepairRepository.readAll();
		if (list == null || list.isEmpty())
			throw new PropertyRepairException("The list of property repairs cannot be read.");
		return list;
	}

	@Override
	public PropertyRepair displayPropertyRepair(Long id) throws PropertyRepairException {
		PropertyRepair propertyRepair = propertyRepairRepository.read(id).orElse(null);
		if(propertyRepair == null)
			throw new PropertyRepairException("The property repair cannot be found.");
		return propertyRepair;
	}

	@Override
	public PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair) throws PropertyRepairException {
		Optional<PropertyRepair> propertyRepairDb = propertyRepairRepository.update(propertyRepair);
		if (propertyRepairDb.isEmpty())
			throw new PropertyRepairException("The repair has not been updated.");
		return propertyRepairDb.orElse(null);
	}
	@Override
	public boolean deletePropertyRepair(Long id) throws PropertyRepairException {
		boolean deleted = propertyRepairRepository.delete(id);
		if (!deleted)
			throw new PropertyRepairException("The repair could not be deleted.");
		return deleted;
	}

	@Override
	public List<PropertyRepair> searchByDateBetween(LocalDate d1, LocalDate d2) throws PropertyRepairException {
		List<PropertyRepair> listOfPropertyRepairs = propertyRepairRepository.readByDateBetween(d1, d2);
		if (listOfPropertyRepairs.isEmpty())
			throw new PropertyRepairException("The repairs with dates " + d1 + " and" + d2 + " could not be found.");
		return listOfPropertyRepairs;

	}
	@Override
	public List<PropertyRepair> searchByOwnerId(Long id) throws PropertyRepairException{
		List<PropertyRepair> listOfPropertiesRepairs = propertyRepairRepository.readByOwnerId(id);
		if (listOfPropertiesRepairs == null || listOfPropertiesRepairs.isEmpty())
			throw new PropertyRepairException("The repairs with owner id " + id + " could not be found.");
		return listOfPropertiesRepairs;
	}
}