package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.FixedRandom;
import com.xode.spaceTrader.util.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {
    private List<Goods> goods;
    private Map<Goods, Integer> goodsPrice;
    private static final int MAX_GOODS = 12;

    public Market(TechLevel techLevel) {
        this.goods = new ArrayList<>();
        this.goodsPrice = new HashMap<>();
        ArrayList<Goods> possible = techLevel.getAvailableGoods();
        int added = 0;
        while (added < MAX_GOODS && !possible.isEmpty()) {
            this.goods.add(possible.remove(FixedRandom.nextInt(possible.size())));
            added++;
        }
        for (Goods good: Goods.values()) {
            int basePrice = good.getBasePrice(techLevel);
            int marketPrice = Calculator.getRandomPrice(basePrice);
            goodsPrice.put(good, marketPrice);
        }
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public int getPrice(Goods good) {
        return goodsPrice.get(good);
    }

    public int getNumGoods() {
        return goods.size();
    }

    public void replaceGoodsWithUniverse(int idx) {
        goods.set(idx, Goods.UNIVERSE);
    }
}
