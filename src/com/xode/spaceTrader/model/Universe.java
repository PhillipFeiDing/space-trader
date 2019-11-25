package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.FixedRandom;

import java.util.List;

public class Universe {
    private Region[] regions;

    private static final int[][] BOUNDS = {
            {-127, -173, -127, -173},
            {-27, -73, -127, -173},
            {73, 27, -127, -173},
            {173, 127, -127, -173},
            {-127, -173, -27, -73},
            {-27, -73, -27, -73},
            {73, 27, -27, -73},
            {173, 127, -27, -73},
            {-127, -173, 73, 27},
            {-27, -73, 73, 27},
            {73, 27, 73, 27},
            {173, 127, 73, 27},
            {-127, -173, 173, 127},
            {-27, -73, 173, 127},
            {73, 27, 173, 127},
            {173, 127, 173, 127}
    };

    private static final Universe SINGLETON = new Universe();

    private Universe() {
        regions = new Region[16];
        List<String> names = Game.getRegionNames();
        for (int i = 0; i < regions.length; i++) {
            String name = names.remove(FixedRandom.nextInt(names.size()));
            regions[i] = new Region(name, BOUNDS[i][0], BOUNDS[i][1], BOUNDS[i][2], BOUNDS[i][3]);
        }

        Region regionToBuyUniverse = regions[FixedRandom.nextInt(regions.length)];
        int replaceIndex = FixedRandom.nextInt(regionToBuyUniverse.getMarket().getNumGoods());
        regionToBuyUniverse.getMarket().replaceGoodsWithUniverse(replaceIndex);
    }

    public static Universe getSingleton() {
        return SINGLETON;
    }

    public Region[] getRegions() {
        return regions;
    }
}
