package com.technico.service.impl;

import java.util.List;
import java.util.Optional;

import com.technico.exception.OwnerException;
import com.technico.exception.PropertyException;
import com.technico.model.Owner;
import com.technico.repository.OwnerRepository;
import com.technico.service.OwnerService;


public class OwnerServiceImpl implements OwnerService{

	private OwnerRepository ownerRepository;
	
	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public void addOwner(Owner owner) throws OwnerException {
		Optional<Owner> ownerDb = ownerRepository.create(owner);
		if(ownerDb.isEmpty())
			throw new OwnerException("The owner has not been saved.");
	}
	
	@Override
	public Owner updateOwner(Owner owner) throws OwnerException {
		Optional<Owner> ownerDb = ownerRepository.update(owner);
		if(ownerDb.isEmpty()) 
			throw new OwnerException("The owner has not been updated.");
		return ownerDb.orElse(null);
			
	}

	@Override
	public void deleteOwner(long id) throws OwnerException {
		if(!ownerRepository.delete(id))
			throw new OwnerException("The owner has not been deleted.");
	}
	
	@Override
	public List<Owner> readAllOwners() throws OwnerException {
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