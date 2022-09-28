package com.technico.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.technico.model.Property;
import com.technico.repository.Repository;

public class PropertyRepositoryImpl implements Repository<Property, String> {

	private final List<Property> listOfProperties;
	private Connection connection;
	
	public PropertyRepositoryImpl(Connection connection,List<Property> listOfProperties) {
		this.connection = connection;
		this.listOfProperties = listOfProperties;
	}
	
	//DEPRECATED
	@Override
	public Property create(Property domain) throws Exception {
		String sql = """
				insert into property (propertyId,
				property_address,
				year_of_construction,
				property_type) values
				(?,?,?,?)
				""";
		try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
			prepareStatement.setString(1,domain.getPropertyIdNumber());
			prepareStatement.setString(2, domain.getPropertyAddress());
			prepareStatement.setString(3, domain.getYearOfConstruction());
			prepareStatement.setString(4, domain.getPropertyType().toString());
			prepareStatement.executeUpdate();
			System.out.println("-- Created: "+ domain);
		} catch (SQLException e) {
			System.out.println("An error occured. Details: " + e.getMessage());
		}
		listOfProperties.add(domain);
		return domain;
	}

	@Override
	public Property read(String id) throws Exception {
		return listOfProperties.stream().filter(p->p.getPropertyIdNumber().equals(id)).findFirst().orElse(null);
	}

	@Override
	public List<Property> readAll() throws Exception {
		return listOfProperties;
	}

	@Override
	public Property update(Property domain) throws Exception {
		Property property = listOfProperties.stream().filter(p->p.getPropertyIdNumber().equals(domain.getPropertyIdNumber())).findFirst().orElse(null);
		if(property!=null) {
			property.setOwnerVAT(domain.getOwnerVAT());
			property.setPropertyAddress(domain.getPropertyAddress());
			property.setPropertyType(domain.getPropertyType());
			property.setYearOfConstruction(domain.getYearOfConstruction());
		}
		return property;
	}

	public boolean delete(String id) throws Exception {
		Property p = read(id);
		return listOfProperties.remove(p);
	}

}
