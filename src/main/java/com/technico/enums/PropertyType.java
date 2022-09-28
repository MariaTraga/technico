package com.technico.enums;

public enum PropertyType {
	DETACHED("Detached house"),
	MAISONETTE("Maisonette"),
	APARTMENT("Apartment building");

    private final String name;

    private PropertyType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * From String method that returns the enumerator value for a given string
     *
     * @param text
     * @return
     */
    public static PropertyType fromString(String text) {
        for (PropertyType pType : PropertyType.values()) {
            if (pType.name.equalsIgnoreCase(text)) {
                return pType;
            }
        }
        return null;
    }
    
}
