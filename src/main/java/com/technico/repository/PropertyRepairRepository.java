package com.technico.repository;

import java.time.LocalDate;
import java.util.List;

import com.technico.exception.PropertyRepairException;
import com.technico.model.PropertyRepair;

public interface PropertyRepairRepository extends Repository<PropertyRepair, Long>{
	
	public List<PropertyRepair> readByDateBetween(LocalDate d1, LocalDate d2) throws PropertyRepairException;
	public List<PropertyRepair> readByOwnerId(long ownerId) throws PropertyRepairException;
}
