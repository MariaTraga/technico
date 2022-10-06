package com.technico.service;

import java.util.List;


import com.technico.exception.OwnerException;
import com.technico.model.Owner;



public interface OwnerService {
	
	void addOwner(Owner owner) throws OwnerException;
	Owner updateOwner(Owner owner) throws OwnerException;
	void deleteOwner(long id) throws OwnerException;
	List<Owner> readAllOwners() throws OwnerException;
	public Owner searchVAT(String in) throws OwnerException;
	public Owner searchEmail(String in) throws OwnerException;

}