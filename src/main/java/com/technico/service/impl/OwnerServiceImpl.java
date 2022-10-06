package com.technico.service.impl;

import java.util.List;
import java.util.Optional;

import com.technico.exception.InvalidEmailException;
import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;
import com.technico.exception.PropertyRepairException;
import com.technico.model.Owner;
import com.technico.model.PropertyRepair;
import com.technico.repository.OwnerRepository;
import com.technico.service.OwnerService;


public class OwnerServiceImpl implements OwnerService{

	private OwnerRepository ownerRepository;
	
	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public Owner addOwner(Owner owner) throws OwnerException {
		Optional<Owner> ownerDb = ownerRepository.create(owner);
		if(ownerDb.isEmpty())
			throw new OwnerException("The owner has not been saved.");
		return ownerDb.orElse(null);
	}
	
	@Override
	public Owner updateOwner(Owner owner) throws OwnerException, InvalidEmailException {
		Optional<Owner> ownerDb = ownerRepository.update(owner);
		if(ownerDb.isEmpty()) 
			throw new OwnerException("The owner has not been updated.");
		return ownerDb.orElse(null);
			
	}
	
	@Override
	public Owner showOwner(Long id) throws OwnerException {
		return ownerRepository.read(id).orElse(null);
	}
	
	@Override
	public void deleteOwner(long id) throws OwnerException {
		if(!ownerRepository.delete(id))
			throw new OwnerException("The owner has not been deleted.");
	}
	
	@Override
	public List<Owner> showAllOwners() throws OwnerException {
		return ownerRepository.readAll();
	}
	
	@Override
	public Owner searchVAT(String in) throws OwnerException{
		Optional<Owner> ownerDb = ownerRepository.readVAT(in);
		if (ownerDb.isEmpty())
			throw new OwnerException("owner with VAT: " + in + "is not found");
		return ownerDb.get();

	}
	
	@Override
	public Owner searchEmail(String in) throws OwnerException{
		Optional<Owner> ownerDb = ownerRepository.readEmail(in);
		if (ownerDb.isEmpty())
			throw new OwnerException("owner with Email: " + in + "is not found");
		return ownerDb.get();
	}
}