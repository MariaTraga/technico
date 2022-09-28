package com.technico.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.technico.enums.RepairType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PropertyRepair {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	
	private LocalDateTime repairDate;
	private String shortDescription;
	private RepairType repairType;
	private BigDecimal repairCost;
	private Owner owner;
	private Property property;
	private String description;
	
	private boolean deleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(LocalDateTime repairDate) {
		this.repairDate = repairDate;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public RepairType getRepairType() {
		return repairType;
	}

	public void setRepairType(RepairType repairType) {
		this.repairType = repairType;
	}

	public BigDecimal getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(BigDecimal repairCost) {
		this.repairCost = repairCost;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public PropertyRepair(long id, LocalDateTime repairDate, String shortDescription, RepairType repairType,
			BigDecimal repairCost, Owner owner, Property property, String description, boolean deleted) {
		super();
		this.id = id;
		this.repairDate = repairDate;
		this.shortDescription = shortDescription;
		this.repairType = repairType;
		this.repairCost = repairCost;
		this.owner = owner;
		this.property = property;
		this.description = description;
		this.deleted = deleted;
	}

	public PropertyRepair() {
		super();
	}

	@Override
	public String toString() {
		return "PropertyRepair [id=" + id + ", repairDate=" + repairDate + ", shortDescription=" + shortDescription
				+ ", repairType=" + repairType + ", repairCost=" + repairCost + ", property=" + property
				+ ", description=" + description + ", deleted=" + deleted + "]";
	}
	
	
}
