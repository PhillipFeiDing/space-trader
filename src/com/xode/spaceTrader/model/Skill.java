package com.xode.spaceTrader.model;

public enum Skill {
    PILOT("Pilot"),
    FIGHTER("Fighter"),
    ENGINEER("Engineer"),
    MERCHANT("Merchant");

    private String alias;
    
    Skill(String alias) {
        this.alias = alias;
    }

    public String toString() {
        return alias;
    }
}
