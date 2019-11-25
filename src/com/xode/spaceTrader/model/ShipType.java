package com.xode.spaceTrader.model;

public enum ShipType {
    FLY("Fly", 50),
    STARSHIP("Starship", 100),
    JET("Jet", 200),
    WASP("Wasp", 400),
    LADYBUG("Ladybug", 800);

    private String alias;
    private Integer value;

    ShipType(String alias, Integer value) {
        this.alias = alias;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.alias;
    }

    public int getValue() {
        return this.value;
    }

}
