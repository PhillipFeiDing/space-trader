package com.xode.spaceTrader.model;

public enum Difficulty {
    EASY("Easy", 16, 1000, 1.0, 0.2, 0.1, 0.3, 0.1),
    MEDIUM("Medium", 12, 500, 1.5, 0.3, 0.2, 0.2, 0.2),
    HARD("Hard", 8, 100, 2.0, 0.5, 0.3, 0.2, 0.3);

    private String alias;
    private Integer initialCredits;
    private Integer skillPoints;
    private Double fuelMultiplier;
    private Double npcMultiplier;
    private Double[] npcRatios;

    Difficulty(String alias, Integer skillPoints, Integer initialCredits, Double fuelMultiplier,
               Double npcMultiplier, Double... npcRatios) {
        this.skillPoints = skillPoints;
        this.initialCredits = initialCredits;
        this.fuelMultiplier = fuelMultiplier;
        this.alias = alias;
        this.npcMultiplier = npcMultiplier;
        this.npcRatios = npcRatios;
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public Integer getInitialCredits() {
        return initialCredits;
    }

    public Double getFuelMultiplier() {
        return fuelMultiplier;
    }

    public Double getNPCMultiplier() {
        return npcMultiplier;
    }

    public Double[] getNpcRatios() {
        return npcRatios;
    }

    @Override
    public String toString() {
        return this.alias;
    }
}
