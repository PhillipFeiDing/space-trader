package com.xode.spaceTrader.model;

import com.xode.spaceTrader.presenter.Main;
import com.xode.spaceTrader.presenter.UniverseMap;
import com.xode.spaceTrader.util.FixedRandom;

import java.util.*;

public class Region {

    private String name;
    private TechLevel techLevel;
    private int x;
    private int y;
    private Market market;
    public static final int XY_RANGE = 400;
    private static final List<TechLevel> TECH_LEVEL_TABLE = new ArrayList<TechLevel>() {
        {
            add(TechLevel.PRE_AGRICULTURAL);
            add(TechLevel.PRE_AGRICULTURAL);
            add(TechLevel.AGRICULTURAL);
            add(TechLevel.AGRICULTURAL);
            add(TechLevel.AGRICULTURAL);
            add(TechLevel.MEDIEVAL);
            add(TechLevel.MEDIEVAL);
            add(TechLevel.RENAISSANCE);
            add(TechLevel.RENAISSANCE);
            add(TechLevel.INDUSTRIAL);
            add(TechLevel.INDUSTRIAL);
            add(TechLevel.MODERN);
            add(TechLevel.MODERN);
            add(TechLevel.MODERN);
            add(TechLevel.FUTURISTIC);
            add(TechLevel.FUTURISTIC);
        }
    };

    public Region(String name, int maxX, int minX, int maxY, int minY) {
        this.name = name;
        this.x = FixedRandom.nextInt(maxX - minX + 1) + minX;
        this.y = FixedRandom.nextInt(maxY - minY + 1) + minY;
        int index = FixedRandom.nextInt(TECH_LEVEL_TABLE.size());
        this.techLevel = TECH_LEVEL_TABLE.remove(index);
        this.market = new Market(techLevel);
    }

    public int distanceCalculator(Region r) {
        return (int) Math.pow(Math.pow(this.getX() - r.getX(), 2)
                + Math.pow(this.getY() - r.getY(), 2), 0.5);
    }

    public int getFrameX() {
        return (int) (x * ((double) Main.HEIGHT / XY_RANGE)
                + (Main.HEIGHT - UniverseMap.REGION_SIDE_LENGTH) / 2.0);
    }

    public int getFrameY() {
        double temp = y * ((double) Main.HEIGHT / XY_RANGE)
                + (Main.HEIGHT - UniverseMap.REGION_SIDE_LENGTH) / 2.0;
        return (int) (temp + (Main.HEIGHT / 2.0 - temp) * 2 - UniverseMap.REGION_SIDE_LENGTH);
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Market getMarket() {
        return market;
    }

    public String toString() {
        return "This region is " + name + " at coordinates: " + x + ", " + y
                + " with tech level " + techLevel.toString()
                + ". This region's position on the frame is " + this.getFrameX() + ", "
                + this.getFrameY();
    }
}
