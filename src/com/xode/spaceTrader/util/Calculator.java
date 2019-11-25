package com.xode.spaceTrader.util;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.model.Goods;
import com.xode.spaceTrader.model.Skill;

import java.util.List;

public class Calculator {

    private static Game game;
    private static final Double BUY_SELL_PRICE_RATIO = 1.2;
    private static final Double MERCHANT_MULTIPLIER = 0.01;
    private static final Double FLUCTUATION = 0.05;
    private static final Double PILOT_MULTIPLIER = 0.02;
    private static final Double ENGINEER_MULTIPLIER = 0.05;
    private static final Double KARMA_MULTIPLIER = 0.01;
    private static final Integer[] SKILL_TO_SUCCESS_RATE = {
        10, 20, 30, 40, 50, 53, 57, 60, 63, 67, 70, 73, 77, 80, 83, 87, 90
    };

    public static void setGame(Game game) {
        Calculator.game = game;
    }

    public static int getRandomPrice(int basePrice) {
        return fluctuate(basePrice);
    }

    public static int getSellDealPrice(int originalPrice) {
        double skill = game.getPlayer().getSkill(Skill.MERCHANT);
        return Math.min((int) Math.round(originalPrice * (1 + MERCHANT_MULTIPLIER * skill)),
                getBuyDealPrice(originalPrice));
    }

    public static int getBuyDealPrice(int originalPrice) {
        double skill = game.getPlayer().getSkill(Skill.MERCHANT);
        return (int) Math.round(getBuyOriginalPrice(
                originalPrice) * (1 - MERCHANT_MULTIPLIER * skill)
                * (1 - game.getKarma() * KARMA_MULTIPLIER));
    }

    public static int getSellOriginalPrice(int originalPrice) {
        return originalPrice;
    }

    public static int getBuyOriginalPrice(int originalPrice) {
        return (int) Math.round((originalPrice * BUY_SELL_PRICE_RATIO));
    }

    public static int getPriceDiscount() {
        return (int) Math.round(game.getPlayer().getSkill(
                Skill.MERCHANT) * MERCHANT_MULTIPLIER * 100);
    }

    public static int getFuelCost(int distance) {
        return (int) Math.round(distance * game.getDifficulty().getFuelMultiplier()
                * (1 - game.getPlayer().getSkill(Skill.PILOT) * PILOT_MULTIPLIER));
    }

    public static int getFuelDiscount() {
        return (int) Math.round(game.getPlayer().getSkill(Skill.PILOT) * PILOT_MULTIPLIER * 100);
    }

    public static int getShipDamage() {
        int damage = fluctuate((int) Math.round(game.getPlayer().getShip().getHealthCapacity()
                * game.getDifficulty().getNPCMultiplier()));
        damage = Math.min(damage, game.getPlayer().getShip().getCurrentHealth());
        return damage;
    }

    public static int getFineAmount(List<Goods> stolenItems) {
        int fine = 0;
        for (Goods stolenItem : stolenItems) {
            fine += stolenItem.getBasePrice(game.getPlayer().getRegion().getTechLevel())
                    * game.getDifficulty().getNPCMultiplier();
        }
        fine = Math.min(fine, game.getPlayer().getCredits());
        return fine;
    }

    public static boolean getEventOutcome(int skill) {
        return (FixedRandom.nextInt(100) < SKILL_TO_SUCCESS_RATE[skill]);
    }

    private static int fluctuate(int amount) {
        int min = (int) Math.round(amount - amount * FLUCTUATION);
        int range = Math.max((int) Math.ceil(amount * 2 * FLUCTUATION), 1);
        return FixedRandom.nextInt(range) + min;
    }
}
