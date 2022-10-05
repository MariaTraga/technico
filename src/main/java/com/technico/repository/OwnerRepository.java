package com.technico.repository;


import java.util.Optional;

import com.technico.exception.OwnerException;
import com.technico.model.Owner;

public interface OwnerRepository extends Repository<Owner ,Long> {

	public Optional<Owner> readVAT(String ownerVAT) throws OwnerException;
	public Optional<Owner> readAddress(String address) throws OwnerException;
	public Optional<Owner> readEmail(String email) throws OwnerException;
	public Optional<Owner> readPassword(String password) throws OwnerException;
}