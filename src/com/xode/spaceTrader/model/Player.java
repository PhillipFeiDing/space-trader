package com.xode.spaceTrader.model;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Player implements Serializable {
    
    private String name;
    private Integer credits;
    private Map<Skill, Integer> skills;
    private Region region;
    private Ship ship;

    public Player(String name, Integer credits, Region region, Ship ship) {
        this.name = name;
        this.credits = credits;
        this.skills = new HashMap<>();
        this.region = region;
        this.ship = ship;
    }
    public Player() {
        this("", null, null, new Ship(ShipType.FLY, 5, 500, 500));
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return this.credits;
    }
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    public Region getRegion() {
        return this.region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }

    public void setSkill(Skill skill, Integer points) {
        skills.put(skill, points);
    }

    public Ship getShip() {
        return this.ship;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Integer getSkill(Skill skill) {
        return skills.getOrDefault(skill, 0);
    }

    public String toString() {
        return "Name: " + name + "\nCredit: " + credits.toString() + "\nSkills" + skills.toString();
    }
}
