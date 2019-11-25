package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncounterPolice extends PagePresenter {
    private JPanel panelMain;
    private JButton forfeitButton;
    private JButton fleeButton;
    private JButton fightButton;
    private JButton statusButton;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JLabel policeLabel;
    private JLabel claimLabel;
    private JLabel itemsLabel;
    private JLabel stolenLabel;

    private Game game;

    private PagePresenter gameInfo;
    private PagePresenter eventOutcome;
    private PagePresenter universeMap;

    public EncounterPolice() {
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(gameInfo);
            }
        });

        forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                Police police = (Police) game.getNpc();
                police.takeItems(player);
                player.setRegion(nextRegion);
                game.setEventLog("You have forfeited " + police.stolenItemsList() + ".\n"
                        + "Now you continue to " + nextRegion.getName() + ".\n");
                game.incKarma();
                goToPage(eventOutcome);
            }
        });

        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Ship ship = game.getPlayer().getShip();
                if (Calculator.getEventOutcome(player.getSkill(Skill.PILOT))) {
                    player.setRegion(player.getRegion());
                    game.setEventLog("You have successfully fled!\n"
                            + "Now you are in your original region "
                            + player.getRegion().getName() + "!\n");
                } else {
                    Police police = (Police) game.getNpc();
                    police.takeItems(player);
                    int fine = police.giveFine(player);
                    int shipDamage = police.damageShip(ship);
                    int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                    Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                    player.setRegion(nextRegion);
                    game.setEventLog("Unfortunately, you failed to flee!\n"
                            + "The police gave your $" + fine + " fine\nand took "
                            + police.stolenItemsList() + "\nand damaged your ship health by "
                            + shipDamage + ".\n"
                            + "You continue to your destination " + nextRegion.getName() + ".\n");
                }
                game.decKarma();
                goToPage(eventOutcome);
            }
        });

        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Ship ship = game.getPlayer().getShip();
                int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                if (Calculator.getEventOutcome(player.getSkill(Skill.FIGHTER))) {
                    player.setRegion(nextRegion);
                    game.setEventLog("You have successfully fought against the police!\n"
                            + "Now you are in your destination region, " + nextRegion.getName()
                            + "!\n");
                    goToPage(eventOutcome);
                } else {
                    Police police = (Police) game.getNpc();
                    police.takeItems(player);
                    int fine = police.giveFine(player);
                    int shipDamage = police.damageShip(ship);
                    player.setRegion(nextRegion);
                    game.setEventLog("Unfortunately, you failed to fight against the police!\n"
                            + "The police gave your $" + fine + " fine\nand took "
                            + police.stolenItemsList() + "and\ndamaged your"
                            + "ship health by " + shipDamage + ".\n"
                            + "You continue to your destination " + nextRegion.getName() + ".\n");
                    goToPage(eventOutcome);
                }
                game.decKarma();
            }
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
        Police police = (Police) game.getNpc();
        itemsLabel.setText(police.stolenItemsList());
        if (police.getStolenItems().size() == 1) {
            stolenLabel.setText("is a stolen item.");
        } else {
            stolenLabel.setText("are stolen items.");
        }
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
        UIStyle.addTransparencyStyle(panelMain, upperPanel, lowerPanel);
        UIStyle.addDisplayTextColorStyle(policeLabel, claimLabel, itemsLabel, stolenLabel);
        UIStyle.addButtonStyle(forfeitButton, fleeButton, fightButton, statusButton);
    }
}
