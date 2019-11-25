package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.Util;

public enum Goods {
    WATER("Water"),
    FOOD("Food"),
    FLINT("Flint"),
    FUR("Fur"),
    WOOD("Wood"),
    ORE("Ore"),
    CLOTHES("Clothes"),
    GOLD("Gold"),
    SPEAR("Spear"),
    SHIELD("Shield"),
    ARMOR("Armor"),
    PAINTING("Painting"),
    CANNON("Cannon"),
    MEDICINE("Medicine"),
    RIFLE("Rifle"),
    COMPUTER("Computer"),
    PHONE("Phone"),
    ROBOT("Robot"),
    DRONE("Drone"),
    LASER("Laser"),
    UNIVERSE("Universe");

    private static final int[][] PRICES = {
            {12, 10, 8, 6, 6, 5, 5},
            {18, 15, 14, 11, 12, 9, 9},
            {43, 39, 35, 31, 32, 49, 56},
            {65, 78, 54, 63, 90, 114, 125},
            {20, 24, 24, 35, 51, 67, 76},
            {35, 42, 49, 80, 60, 57, 53},
            {32, 27, 25, 26, 20, 22, 23},
            {50, 55, 67, 70, 80, 86, 85},
            {80, 77, 115, 81, 92, 53, 48},
            {250, 240, 230, 230, 210, 200, 190},
            {145, 136, 127, 127, 109, 100, 91},
            {87, 134, 173, 200, 256, 276, 294},
            {627, 530, 420, 340, 300, 430, 500},
            {120, 136, 154, 90, 74, 78, 57},
            {240, 280, 300, 240, 220, 198, 175},
            {1500, 1430, 1200, 990, 700, 550, 525},
            {980, 869, 745, 699, 570, 530, 550},
            {6370, 6100, 5650, 4900, 4430, 3675, 3140},
            {2700, 4000, 3000, 3300, 2890, 2400, 2650},
            {2400, 2800, 3400, 3845, 3200, 3000, 2750},
            {12000, 12000, 12000, 12000, 12000, 12000, 12000}
    };

    private String alias;

    Goods(String alias) {
        this.alias = alias;
    }

    public int getBasePrice(TechLevel techLevel) {
        return PRICES[Util.findIdxInArray(this, Goods.values())]
                [Util.findIdxInArray(techLevel, TechLevel.values())];
    }

    public String toString() {
        return this.alias;
    }

}
