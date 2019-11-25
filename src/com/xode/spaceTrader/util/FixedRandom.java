package com.xode.spaceTrader.util;

import java.util.Random;

public class FixedRandom {
    private static int seed = 0;
    private static Random trueRandom = new Random(seed);

    public static int nextInt(int param) {
        return trueRandom.nextInt(param);
    }

    public static double nextDouble() {
        return trueRandom.nextDouble();
    }
}
