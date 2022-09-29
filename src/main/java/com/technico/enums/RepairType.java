package com.technico.enums;

public enum RepairType {
	PAINTING("Painting"), INSULATION("Insulation"), FRAMES("Frames"), PLUMBING("Plumbing"),
	ELECTRICAL("Electrical Work");

	private final String repair;

	private RepairType(String repair) {
		this.repair = repair;
	}

	@Override
	public String toString() {
		return repair;
	}

	public static RepairType fromString(String text) {
		for (RepairType rType : RepairType.values()) {
			if (rType.repair.equalsIgnoreCase(text)) {
				return rType;
			}
		}
		return null;
	}
}