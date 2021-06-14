package com.gb.fineos.domain;

import java.util.stream.Stream;

public enum ClaimType {
    MOTOR_CLAIM("MOTORCLAIM"),
    PERSONAL_INJURY_CLAIM("PERSONALINJURY"),
    PROPERTY_DAMAGE_CLAIM("PROPERTYDAMAGE"),
    PROPERTY_CLAIM("PROPERTYCLAIM"),
    MISC_INJURY_CLAIM("MISC_INJURYCLAIM"),
    MISC_PROPERTY_CLAIM("MISC_PROPERTYCLAIM"),
    HEALTH_CLAIM("HEALTHINJURY");

    private final String property;

    ClaimType(final String property) {
        this.property = property;
    }

    public static ClaimType valueOfProperty(final String property) {
        return Stream.of(values()).filter(e -> e.property.equalsIgnoreCase(property)).findFirst().orElse(null);
    }
}
