package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.model.Player;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentRegion extends PagePresenter {
    private JPanel panelMain;
    private JPanel buttonPanel;
    private JPanel regionInfoPanel;
    private JButton travelButton;
    private JButton marketButton;
    private JButton shipyardButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton statusButton;
    private JLabel currentRegionLabel;
    private JLabel currentRegionValueLabel;
    private JLabel techLevelLabel;
    private JLabel techLevelValueLabel;
    private JLabel coordinatesLabel;
    private JLabel xLabel;
    private JLabel yLabel;

    private PagePresenter universeMap;
    private PagePresenter regionMarket;
    private PagePresenter gameInfo;
    private PagePresenter shipyard;

    private Game game;

    public CurrentRegion() {
        travelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(universeMap);
            }
        });
        marketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(regionMarket);
            }
        });
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(gameInfo);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        shipyardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(shipyard);
            }
        });
    }

    void setUniverseMap(PagePresenter universeMap) {
        this.universeMap = universeMap;
    }

    void setRegionMarket(PagePresenter regionMarket) {
        this.regionMarket = regionMarket;
    }

    void setGameInfo(PagePresenter gameInfo) {
        this.gameInfo = gameInfo;
    }

    void setShipyard(PagePresenter shipyard) {
        this.shipyard = shipyard;
    }

    void setGame(Game game) {
        this.game = game;
    }

    JPanel getPanelMain() {
        return panelMain;
    }

    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    void syncModelView() {
        Player player = game.getPlayer();
        currentRegionValueLabel.setText(player.getRegion().getName());
        techLevelValueLabel.setText(player.getRegion().getTechLevel().toString());
        xLabel.setText("X: " + player.getRegion().getX());
        yLabel.setText("Y: " + player.getRegion().getY());
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, buttonPanel, regionInfoPanel);
        UIStyle.addButtonStyle(travelButton, marketButton, shipyardButton,
                exitButton, saveButton, statusButton);
        UIStyle.addDisplayTextColorStyle(currentRegionLabel, currentRegionValueLabel,
                techLevelLabel, techLevelValueLabel, coordinatesLabel, xLabel, yLabel);
        UIStyle.addPanelTranslucencyStyle(regionInfoPanel);
    }
}
