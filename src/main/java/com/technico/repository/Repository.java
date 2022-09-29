package com.technico.repository;

import java.util.List;

public interface Repository<D, ID> {

	
	public D create(D domain) throws Exception;
	public D read(ID id) throws Exception;
	public List<D> readAll() throws Exception;
	public D update(D domain) throws Exception;
	public boolean delete(ID id) throws Exception;
	
}
