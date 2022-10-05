package com.technico.model;

import com.technico.enums.PropertyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Property {
	
	//TODO Add owner foreign key
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String propertyIdNumber;
	private String propertyAddress;
	private String yearOfConstruction;
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;	
	private String ownerVAT;
	//TODO Connect(?) foreign key
	private long ownerId;
	
	private boolean deleted;
	@ManyToOne
	private Owner owner;
	
	public String getPropertyIdNumber() {
		return propertyIdNumber;
	}
	public void setPropertyIdNumber(String propertyId) {
		this.propertyIdNumber = propertyId;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getYearOfConstruction() {
		return yearOfConstruction;
	}
	public void setYearOfConstruction(String yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}
	public PropertyType getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	public String getOwnerVAT() {
		return ownerVAT;
	}
	public void setOwnerVAT(String ownerVAT) {
		this.ownerVAT = ownerVAT;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public Property(String propertyId, String propertyAddress, String yearOfConstruction, PropertyType propertyType,
			String ownerVAT,long ownerId, boolean deleted ) {
		super();
		this.propertyIdNumber = propertyId;
		this.propertyAddress = propertyAddress;
		this.yearOfConstruction = yearOfConstruction;
		this.propertyType = propertyType;
		this.ownerVAT = ownerVAT;
		this.ownerId = ownerId;
		this.deleted = deleted;
	}
	public Property() {
		super();
	}
	
	@Override
	public String toString() {
		return "Property [id=" + id + ", propertyId=" + propertyIdNumber + ", propertyAddress=" + propertyAddress
				+ ", yearOfConstruction=" + yearOfConstruction + ", propertyType=" + propertyType + ", ownerVAT="
				+ ownerVAT + "]";
	}
	
	
		
}
