package com.technico.repository;

import java.util.List;
import java.util.Optional;

import com.technico.model.Property;

public interface PropertyRepository extends Repository<Property,Long> {
	
	public Optional<Property> readByIdNumber(String propertyIdNumber);
	public List<Property> readByOwnerVAT(String ownerVAT);
	public boolean deleteSafe(Long id);
}
