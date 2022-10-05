package com.technico.model;


//import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.technico.enums.PropertyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
//@SQLDelete(sql = "UPDATE property SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	@Column(unique = true)

	private String propertyIdNumber;
	private String propertyAddress;
	private String yearOfConstruction;
	private boolean deleted;
	@ManyToOne
	private Owner owner;

	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;


	public Property(String propertyIdNumber, String propertyAddress, String yearOfConstruction,
			PropertyType propertyType, Owner owner, boolean deleted) {
		super();
		this.propertyIdNumber = propertyIdNumber;
		this.propertyAddress = propertyAddress;
		this.yearOfConstruction = yearOfConstruction;
		this.propertyType = propertyType;
		this.owner = owner;
		this.deleted = deleted;
	}

	public Property() {
		super();
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPropertyIdNumber() {
		return propertyIdNumber;
	}

	public void setPropertyIdNumber(String propertyIdNumber) {
		this.propertyIdNumber = propertyIdNumber;
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


	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", propertyIdNumber=" + propertyIdNumber + ", propertyAddress=" + propertyAddress
				+ ", yearOfConstruction=" + yearOfConstruction + ", owner=" + owner	+ ", propertyType=" + propertyType + "]";
	}

}
