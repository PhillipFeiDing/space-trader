package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.FixedRandom;

public abstract class NPC {

    private static Game game;

    public static void setGame(Game game) {
        NPC.game = game;
    }

    public int damageShip(Ship ship) {
        int damage = Calculator.getShipDamage();
        ship.setCurrentHealth(ship.getCurrentHealth() - damage);
        return damage;
    }

    public static NPC getNextNPC() {
        Double[] ratios = game.getDifficulty().getNpcRatios();

        double prob = FixedRandom.nextDouble();
        if (prob < ratios[0]) {
            return new Bandit(game);
        }

        prob -= ratios[0];
        if (prob < ratios[1]) {
            return new Trader(game);
        }

        prob -= ratios[1];
        if (prob < ratios[2]) {
            if (game.getPlayer().getShip().getCargoes().size() == 0) {
                return null;
            }
            return new Police(game);
        }

        return null;
    }

    public abstract String getNpcType();
}
