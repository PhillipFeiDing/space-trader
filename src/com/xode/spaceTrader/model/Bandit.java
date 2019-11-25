package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.FixedRandom;

import java.util.List;

public class Bandit extends NPC {
    private static final Integer BASE_DEMAND = 100;
    private static final Integer RANDOM_DEMAND = 100;

    private int banditDemands;

    public Bandit(Game game) {
        banditDemands = (int) ((FixedRandom.nextInt(RANDOM_DEMAND) + BASE_DEMAND)
                * game.getDifficulty().getNPCMultiplier());
    }

    public int robCredits(Player player) {
        player.setCredits(player.getCredits() - banditDemands);
        return banditDemands;
    }

    public int robAllCredits(Player player) {
        int robAmount = player.getCredits();
        player.setCredits(0);
        return robAmount;
    }

    public List<Goods> robAllItems(Player player) {
        return player.getShip().removeAllCargoes();
    }

    public int giveCredits(Player player) {
        player.setCredits(player.getCredits() + banditDemands);
        return banditDemands;
    }

    public int getBanditDemands() {
        return banditDemands;
    }

    public String toString() {
        return "Bandit, demand is " + banditDemands + ".";
    }

    @Override
    public String getNpcType() {
        return "Bandit";
    }
}
