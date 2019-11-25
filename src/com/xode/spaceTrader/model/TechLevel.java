package com.xode.spaceTrader.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TechLevel {
    PRE_AGRICULTURAL("Pre-Agricultural",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR),
    AGRICULTURAL("Agricultural",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR),
    MEDIEVAL("Medieval",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR, Goods.RIFLE),
    RENAISSANCE("Renaissance",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR, Goods.RIFLE, Goods.PAINTING,
            Goods.MEDICINE),
    INDUSTRIAL("Industrial",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR, Goods.RIFLE, Goods.PAINTING,
            Goods.MEDICINE, Goods.CANNON, Goods.COMPUTER, Goods.PHONE, Goods.DRONE, Goods.LASER),
    MODERN("Modern",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR, Goods.RIFLE, Goods.PAINTING,
            Goods.MEDICINE, Goods.CANNON, Goods.COMPUTER, Goods.PHONE, Goods.DRONE, Goods.LASER,
            Goods.ROBOT),
    FUTURISTIC("Futuristic",
            Goods.WATER, Goods.FOOD, Goods.FLINT, Goods.FUR, Goods.WOOD, Goods.ORE, Goods.CLOTHES,
            Goods.GOLD, Goods.SPEAR, Goods.SHIELD, Goods.ARMOR, Goods.RIFLE, Goods.PAINTING,
            Goods.MEDICINE, Goods.CANNON, Goods.COMPUTER, Goods.PHONE, Goods.DRONE, Goods.LASER,
            Goods.ROBOT);

    private String alias;
    private List<Goods> goodsForSale = new ArrayList<>();

    TechLevel(String alias, Goods... goods) {
        this.alias = alias;
        Collections.addAll(goodsForSale, goods);
    }

    public String toString() {
        return alias;
    }

    public ArrayList<Goods> getAvailableGoods() {
        ArrayList<Goods> goods = new ArrayList<>();
        for (Goods good: goodsForSale) {
            goods.add(good);
        }
        return goods;
    }
}
