package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.FixedRandom;

import java.util.ArrayList;
import java.util.List;

public class Trader extends NPC {

    private static final int NUM_GOODS = 10;
    private static final int MAX_GIVE_GOODS = 2;
    private static final double PRICE_FACTOR = 0.5;

    private List<Goods> goods;
    private List<Integer> prices;
    private boolean negotiated;

    public Trader(Game game) {
        this.goods = new ArrayList<>();
        this.prices = new ArrayList<>();
        TechLevel[] allLevels = TechLevel.values();
        TechLevel techLevel = allLevels[FixedRandom.nextInt(allLevels.length)];
        ArrayList<Goods> possible = techLevel.getAvailableGoods();
        int added = 0;
        while (added < NUM_GOODS) {
            this.goods.add(possible.get(FixedRandom.nextInt(possible.size())));
            added++;
        }
        for (Goods good: goods) {
            prices.add(good.getBasePrice(techLevel));
        }
        this.negotiated = false;
    }

    public ArrayList<Goods> giveGoods() {
        ArrayList<Goods> robbed = new ArrayList<>();
        int robbedCount = 0;
        while (robbedCount < MAX_GIVE_GOODS && !goods.isEmpty()) {
            int toRobIdx = FixedRandom.nextInt(goods.size());
            robbed.add(goods.remove(toRobIdx));
            prices.remove(toRobIdx);
            robbedCount++;
        }
        return robbed;
    }

    public void increasePrice() {
        for (int i = 0; i < goods.size(); i++) {
            prices.set(i, (int) Math.round(prices.get(i) * (1 + PRICE_FACTOR)));
        }
    }

    public void decreasePrice() {
        for (int i = 0; i < goods.size(); i++) {
            prices.set(i, (int) Math.round(prices.get(i) * (1 - PRICE_FACTOR)));
        }
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public Goods sell(int idx) {
        Goods ret = goods.remove(idx);
        prices.remove(idx);
        return ret;
    }

    public boolean getNegotiated() {
        return negotiated;
    }

    public void setNegotiated(boolean negotiated) {
        this.negotiated = negotiated;
    }

    public String toString() {
        return "Trader.";
    }

    @Override
    public String getNpcType() {
        return "Trader";
    }
}
