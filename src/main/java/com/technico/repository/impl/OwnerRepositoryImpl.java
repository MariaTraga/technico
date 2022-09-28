package com.technico.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.technico.model.Owner;
import com.technico.repository.Repository;

public class OwnerRepositoryImpl implements Repository<Owner, String> {

	private final List<Owner> listOfOwners;
	private Connection connection;
	
	public OwnerRepositoryImpl(Connection connection,List<Owner> listOfOwners) {
		this.connection = connection;
		this.listOfOwners = listOfOwners;
	}
	
	//DEPRECATED
	@Override
	public Owner create(Owner domain) throws Exception {
		String sql = """
				insert into owner (ownerVAT,
				name,
				surname,
				address,
				phone_number,
				email,
				username,
				password) values
				(?,?,?,?)
				""";
		try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
			prepareStatement.setString(1,domain.getOwnerVAT());
			prepareStatement.setString(2, domain.getName());
			prepareStatement.setString(3, domain.getSurname());
			prepareStatement.setString(4, domain.getAddress());
			prepareStatement.setString(4, domain.getPhoneNumber());
			prepareStatement.setString(4, domain.getEmail());
			prepareStatement.setString(4, domain.getUsername());
			prepareStatement.setString(4, domain.getPassword().toString());
			prepareStatement.executeUpdate();
			System.out.println("-- Created: "+ domain);
		} catch (SQLException e) {
			System.out.println("An error occured. Details: " + e.getMessage());
		}
		listOfOwners.add(domain);
		return domain;
	}

	@Override
	public Owner read(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Owner> readAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Owner update(Owner domain) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
