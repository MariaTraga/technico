package com.technico.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.technico.exception.PropertyRepairException;
import com.technico.model.PropertyRepair;
import com.technico.repository.impl.PropertyRepairRepositoryImpl;
import com.technico.service.PropertyRepairService;

public class PropertyRepairServiceImpl implements PropertyRepairService {
	private PropertyRepairRepositoryImpl propertyRepairRepository;

	public PropertyRepairServiceImpl(PropertyRepairRepositoryImpl propertyRepairRepository) {
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
	public List<PropertyRepair> displayAllPropertyRepairs() {
		return propertyRepairRepository.readAll();
	}

	@Override
	public PropertyRepair displayPropertyRepair(Long id) throws PropertyRepairException {
		return propertyRepairRepository.read(id).orElse(null);
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

	public List<PropertyRepair> searchByDateBetween (Date d1, Date d2) throws PropertyRepairException{
		List<PropertyRepair> listOfPropertyRepairs = propertyRepairRepository.readByDateBetween(d1, d2);
				if(listOfPropertyRepairs.isEmpty())
					throw new PropertyRepairException("The repairs with dates " +d1+" and" +d2+" could not be found.");
					return listOfPropertyRepairs;
				
}
}
