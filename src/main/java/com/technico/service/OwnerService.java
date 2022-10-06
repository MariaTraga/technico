package com.technico.service;

import java.util.List;

import com.technico.exception.OwnerException;
import com.technico.model.Owner;

public interface OwnerService {
	
	/**
	 * Add new owner to database.
	 * @param owner
	 * @return
	 * @throws OwnerException
	 */
	Owner addOwner(Owner owner) throws OwnerException;
	/**
	 * Update owner with specified id to the database.
	 * @param owner
	 * @return
	 * @throws OwnerException
	 */
	Owner updateOwner(Owner owner) throws OwnerException;
	/**
	 * Delete owner safely with specified id to the database.
	 * @param id
	 * @throws OwnerException
	 */
	void deleteOwner(long id) throws OwnerException;
	/**
	 * Read all owners from the database.
	 * @return
	 * @throws OwnerException
	 */
	List<Owner> showAllOwners() throws OwnerException;
	/**
	 * Search owner with a specific VAT.
	 * @param in
	 * @return
	 * @throws OwnerException
	 */
	public Owner searchVAT(String in) throws OwnerException;
	/**
	 * Search owner with a specific email.
	 * @param in
	 * @return
	 * @throws OwnerException
	 */
	public Owner searchEmail(String in) throws OwnerException;
	/**
	 * Read an owner with a specified id from the database. 
	 * @param id
	 * @return
	 * @throws OwnerException
	 */
	Owner showOwner(Long id) throws OwnerException;
}