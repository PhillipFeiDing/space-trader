package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncounterBandit extends PagePresenter {
    private JPanel panelMain;
    private JButton statusButton;
    private JButton payButton;
    private JButton fleeButton;
    private JButton fightButton;
    private JLabel bandit;
    private JLabel banditwants;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private Game game;

    private PagePresenter gameInfo;
    private PagePresenter eventOutcome;
    private PagePresenter universeMap;

    public EncounterBandit() {
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(gameInfo);
            }
        });
        payButton.addActionListener(e -> {
            Bandit banditNPC = (Bandit) game.getNpc();
            int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
            Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
            String event;
            if (banditNPC.getBanditDemands() > game.getPlayer().getCredits()) {
                if (game.getPlayer().getShip().getNumCargoes() == 0) {
                    int amount = banditNPC.damageShip(game.getPlayer().getShip());
                    game.getPlayer().setRegion(nextRegion);
                    event = "Seems that you don't have enough credits to pay.\n"
                            + "And your ship is empty.\n"
                            + "The bandit is very angry and damaged your ship health by "
                            + amount + ".\n" + "You continued to " + nextRegion.getName() + ".\n";
                } else {
                    banditNPC.robAllItems(game.getPlayer());
                    game.getPlayer().setRegion(nextRegion);
                    event = "Seems that you don't have enough credits to pay.\n"
                            + "Instead, the bandit has robbed all of your items!\n"
                            + "You continued to " + nextRegion.getName() + ".\n";
                }
            } else {
                event = "The bandit robbed $" + banditNPC.robCredits(game.getPlayer())
                        + " from you!\n" + "You continued to " + nextRegion.getName() + ".\n";
                game.getPlayer().setRegion(game.getUniverse().getRegions()[nextRegionIdx]);
            }
            game.setEventLog(event);
            goToPage(eventOutcome);
        });

        fleeButton.addActionListener(e -> {
            String event;
            if (Calculator.getEventOutcome(game.getPlayer().getSkill(Skill.PILOT))) {
                event = "You have successfully fled back to "
                        + game.getPlayer().getRegion().getName()
                        + ".\nYou have kept all of your credits and items.\n";
                game.getPlayer().setRegion(game.getPlayer().getRegion());
            } else {
                Bandit banditNPC = (Bandit) game.getNpc();
                int damage = banditNPC.damageShip(game.getPlayer().getShip());
                banditNPC.robAllCredits(game.getPlayer());
                int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                game.getPlayer().setRegion(nextRegion);
                event = "Your flee failed!\n"
                        + "The bandit took all of your credits.\n"
                        + "The bandit also damaged your ship health by " + damage + ".\n"
                        + "You continued to " + nextRegion.getName() + ".\n";
            }
            game.setEventLog(event);
            goToPage(eventOutcome);
        });

        fightButton.addActionListener(e -> {
            String event;
            Bandit banditNPC = (Bandit) game.getNpc();
            int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
            Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
            if (Calculator.getEventOutcome(game.getPlayer().getSkill(Skill.FIGHTER))) {
                int credit = banditNPC.giveCredits(game.getPlayer());
                event = "You have successfully beat the bandit.\n"
                        + "Additionally, you robbed $" + credit + " from her.\n"
                        + "You continued to " + nextRegion.getName() + ".\n";
            } else {
                int damage = banditNPC.damageShip(game.getPlayer().getShip());
                banditNPC.robAllCredits(game.getPlayer());
                event = "Your fight failed!\n"
                        + "The bandit took all of your credits.\n"
                        + "And she damaged your ship health by " + damage + ".\n"
                        + "You continued to " + nextRegion.getName() + ".\n";
            }
            game.getPlayer().setRegion(nextRegion);
            game.setEventLog(event);
            game.incKarma();
            goToPage(eventOutcome);
        });
    }

    void setGameInfo(PagePresenter gameInfo) {
        this.gameInfo = gameInfo;
    }

    void setEventOutcome(PagePresenter eventOutcome) {
        this.eventOutcome = eventOutcome;
    }

    void setUniverseMap(PagePresenter universeMap) {
        this.universeMap = universeMap;
    }

    void setGame(Game game) {
        this.game = game;
    }

    void syncModelView() {
        Bandit banditNPC = (Bandit) game.getNpc();
        banditwants.setText("Bandit wants $" + banditNPC.getBanditDemands());
        renderUIStyle();
    }

    JPanel getPanelMain() {
        return this.panelMain;
    }
    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(topPanel, panelMain, bottomPanel);
        UIStyle.addDisplayTextColorStyle(bandit, banditwants);
        UIStyle.addButtonStyle(statusButton, payButton, fleeButton, fightButton);
    }

}
