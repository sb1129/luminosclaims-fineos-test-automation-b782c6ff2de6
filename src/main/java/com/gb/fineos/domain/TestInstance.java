package com.gb.fineos.domain;

import java.util.stream.Stream;

public enum TestInstance {
    AU("au"), ICARE("icare"), NZ("nz"), UK("uk"), CC("cc");

    private final String property;

    TestInstance(final String property) {
        this.property = property;
    }

    public static TestInstance valueOfProperty(final String property) {
        return Stream.of(values()).filter(e -> e.property.equalsIgnoreCase(property)).findFirst().orElse(null);
    }
}
