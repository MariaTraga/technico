package com.technico.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<D, ID> {

	
	public Optional<D> create(D entity);
	public Optional<D> read(ID id);
	public List<D> readAll();
	public Optional<D> update(D entity);
	public boolean delete(ID id); 
	
}
