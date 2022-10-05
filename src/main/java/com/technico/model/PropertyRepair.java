package com.technico.model;

import java.math.BigDecimal;
import java.sql.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.technico.enums.RepairType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@SQLDelete(sql = "UPDATE propertyRepair SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")

public class PropertyRepair {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Owner owner;
	@ManyToOne 
	private Property property;
	private Date repairDate;
	private String shortDescription;
	private RepairType repairType;
	private BigDecimal repairCost;
	
	private String description;
	
	private boolean deleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
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

	public PropertyRepair(long id, Date repairDate, String shortDescription, RepairType repairType,
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
