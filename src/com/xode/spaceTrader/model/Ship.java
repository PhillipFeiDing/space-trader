package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private ShipType type;
    private int cargoSpaceCapacity;
    private int fuelCapacity;
    private int healthCapacity;
    private int currentFuel;
    private int currentHealth;
    private List<Goods> cargoes;
    private List<Integer> cargoValues;

    public Ship(ShipType type, int cargoSpace, int fuelCapacity, int health) {
        this.type = type;
        this.cargoSpaceCapacity = cargoSpace;
        this.fuelCapacity = fuelCapacity;
        this.healthCapacity = health;
        this.currentFuel = fuelCapacity;
        this.currentHealth = health;
        this.cargoes = new ArrayList<>();
        this.cargoValues = new ArrayList<>();
    }
    public ShipType getType() {
        return this.type;
    }
    public void setType(ShipType type) {
        this.type = type;
    }
    public int getCargoSpaceCapacity() {
        return this.cargoSpaceCapacity;
    }
    public void setCargoSpaceCapacity(int cargoSpaceCapacity) {
        this.cargoSpaceCapacity = cargoSpaceCapacity;
    }
    public int getNumCargoes() {
        return cargoes.size();
    }
    public int getFuelCapacity() {
        return this.fuelCapacity;
    }
    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
    public int getCurrentFuel() {
        return this.currentFuel;
    }
    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }
    public int getHealthCapacity() {
        return this.healthCapacity;
    }
    public void setHealthCapacity(int healthCapacity) {
        this.healthCapacity = healthCapacity;
    }
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public List<Goods> getCargoes() {
        return this.cargoes;
    }
    public List<Integer> getCargoValues() {
        return this.cargoValues;
    }
    public void addCargo(Goods good, int value) {
        cargoes.add(good);
        cargoValues.add(value);
    }
    public Goods removeCargo(int idx) {
        cargoValues.remove(idx);
        return cargoes.remove(idx);
    }
    public Goods removeCargo(Goods good) {
        return removeCargo(Util.findIdxInArray(good, cargoes.toArray()));
    }
    public List<Goods> removeAllCargoes() {
        List<Goods> result = cargoes;
        cargoes = new ArrayList<>();
        cargoValues = new ArrayList<>();
        return result;
    }
    public String toString() {
        return "type is " + type + "\ncargoSpaceCapacity is: " + cargoSpaceCapacity
                + "\ncurrent cargoSpaceCapacity is: " + getNumCargoes()
                + "\nfuel capacity is: " + fuelCapacity + "\ncurrent fuel is: "
                + currentFuel + "\nhealthCapacity is: " + healthCapacity
                + "\ncurrent healthCapacity" + healthCapacity;
    }
}
