package com.technico.service;

import java.util.List;

import com.technico.exception.InvalidEmailException;
import com.technico.exception.OwnerException;
import com.technico.model.Owner;




public interface OwnerService {
	
	Owner addOwner(Owner owner) throws OwnerException;
	Owner updateOwner(Owner owner) throws OwnerException, InvalidEmailException;
	void deleteOwner(long id) throws OwnerException;
	List<Owner> showAllOwners() throws OwnerException;
	public Owner searchVAT(String in) throws OwnerException;
	public Owner searchEmail(String in) throws OwnerException;
	Owner showOwner(Long id) throws OwnerException;
}