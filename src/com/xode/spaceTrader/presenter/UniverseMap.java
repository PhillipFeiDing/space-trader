package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.Stacking;
import com.xode.spaceTrader.util.UIStyle;
import com.xode.spaceTrader.util.Util;

import javax.swing.*;

public class UniverseMap extends PagePresenter {
    private JPanel panelMain;
    private JPanel leftPanel;
    private JPanel coordinatesPanel;
    private JPanel rightPanel;
    private JPanel shipPanel;
    private JPanel regionPanel;
    private JPanel bottomRightPanel;
    private JPanel shipHullPanel;
    private JPanel shipFuelPanel;
    private JPanel hullProgress;
    private JPanel fuelProgress;
    private JPanel region0Panel;
    private JPanel region1Panel;
    private JPanel region2Panel;
    private JPanel region3Panel;
    private JPanel region4Panel;
    private JPanel region6Panel;
    private JPanel region8Panel;
    private JPanel region5Panel;
    private JPanel region7Panel;
    private JPanel region9Panel;
    private JPanel regionAPanel;
    private JPanel regionBPanel;
    private JPanel regionCPanel;
    private JPanel regionDPanel;
    private JPanel regionEPanel;
    private JPanel regionFPanel;
    private JLabel xValueLabel;
    private JLabel yValueLabel;
    private JLabel distanceLabel;
    private JLabel distanceValueLabel;
    private JLabel shipLabel;
    private JLabel regionLabel;
    private JLabel regionValueLabel;
    private JLabel techLevelLabel;
    private JLabel techLevelValueLabel;
    private JLabel coordinatesLabel;
    private JLabel hullLabel;
    private JLabel fuelLabel;
    private JLabel region0Label;
    private JLabel region1Label;
    private JLabel region2Label;
    private JLabel region3Label;
    private JLabel region4Label;
    private JLabel region6Label;
    private JLabel region8Label;
    private JLabel region5Label;
    private JLabel region7Label;
    private JLabel region9Label;
    private JLabel regionALabel;
    private JLabel regionBLabel;
    private JLabel regionCLabel;
    private JLabel regionDLabel;
    private JLabel regionELabel;
    private JLabel regionFLabel;
    private JLabel hullValueLabel;
    private JLabel fuelValueLabel;
    private JLabel fuelCostLabel;
    private JLabel fuelCostValueLabel;
    private JButton visitButton;
    private JButton travelButton;
    private JButton region0Button;
    private JButton region1Button;
    private JButton region2Button;
    private JButton region3Button;
    private JButton region4Button;
    private JButton region6Button;
    private JButton region8Button;
    private JButton region5Button;
    private JButton region7Button;
    private JButton region9Button;
    private JButton regionAButton;
    private JButton regionBButton;
    private JButton regionCButton;
    private JButton regionDButton;
    private JButton regionEButton;
    private JButton regionFButton;
    private JLabel errorMessageLabel;

    private final JPanel[] regionPanels = {
        region0Panel, region1Panel, region2Panel, region3Panel,
        region4Panel, region5Panel, region6Panel, region7Panel,
        region8Panel, region9Panel, regionAPanel, regionBPanel,
        regionCPanel, regionDPanel, regionEPanel, regionFPanel
    };
    private final JLabel[] regionLabels = {
        region0Label, region1Label, region2Label, region3Label,
        region4Label, region5Label, region6Label, region7Label,
        region8Label, region9Label, regionALabel, regionBLabel,
        regionCLabel, regionDLabel, regionELabel, regionFLabel
    };
    private final JButton[] regionButtons = {
        region0Button, region1Button, region2Button, region3Button,
        region4Button, region5Button, region6Button, region7Button,
        region8Button, region9Button, regionAButton, regionBButton,
        regionCButton, regionDButton, regionEButton, regionFButton
    };

    private PagePresenter currentRegion;
    private PagePresenter eventStart;

    private Game game;
    private int nextRegionIdx;

    public static final int REGION_SIDE_LENGTH = 80;

    public UniverseMap() {
        visitButton.addActionListener(e -> {
            nextRegionIdx = Util.findIdxInArray(game.getPlayer().getRegion(),
                    game.getUniverse().getRegions());
            goToPage(currentRegion);
        });
        travelButton.addActionListener(e -> {
            Region[] regions = game.getUniverse().getRegions();
            Player player = game.getPlayer();
            int distance = regions[nextRegionIdx].distanceCalculator(player.getRegion());
            Ship ship = game.getPlayer().getShip();

            game.setNpc(NPC.getNextNPC());
            if (game.getNpc() != null) {
                ship.setCurrentFuel(ship.getCurrentFuel() - Calculator.getFuelCost(distance));
                goToPage(eventStart);
            } else {
                ship.setCurrentFuel(ship.getCurrentFuel() - Calculator.getFuelCost(distance));
                player.setRegion(regions[nextRegionIdx]);
                syncModelView();
            }
        });
        for (JButton regionButton: regionButtons) {
            regionButton.addActionListener(e -> {
                nextRegionIdx = Util.findIdxInArray(regionButton, regionButtons);
                syncModelView();
            });
        }
        nextRegionIdx = -1;
        travelButton.setEnabled(false);
        visitButton.setEnabled(true);
    }

    public int getNextRegionIdx() {
        return this.nextRegionIdx;
    }

    void setCurrentRegion(PagePresenter currentRegion) {
        this.currentRegion = currentRegion;
    }

    void setEventStart(PagePresenter eventStart) {
        this.eventStart = eventStart;
    }

    void setGame(Game game) {
        this.game = game;
        Region[] regions = this.game.getUniverse().getRegions();
        Player player = this.game.getPlayer();
        leftPanel.setLayout(null);
        for (int i = 0; i < regionPanels.length; i++) {
            regionPanels[i].setBounds(
                    regions[i].getFrameX(),
                    regions[i].getFrameY(),
                    REGION_SIDE_LENGTH,
                    REGION_SIDE_LENGTH);
            regionLabels[i].setText(regions[i].getName());
        }
        this.nextRegionIdx = Util.findIdxInArray(player.getRegion(), regions);
    }

    JPanel getPanelMain() {
        return panelMain;
    }

    String getBackgroundImageName() {
        return "space.jpg";
    }

    void syncModelView() {
        Region[] regions = game.getUniverse().getRegions();
        Player player = game.getPlayer();
        Region nextRegion = regions[nextRegionIdx];
        regionValueLabel.setText(nextRegion.getName());
        techLevelValueLabel.setText(nextRegion.getTechLevel().toString());
        xValueLabel.setText("X: " + nextRegion.getX());
        yValueLabel.setText("Y: " + nextRegion.getY());
        int distance = nextRegion.distanceCalculator(player.getRegion());
        int fuelCost = Calculator.getFuelCost(distance);
        distanceValueLabel.setText(Integer.toString(distance));
        fuelCostValueLabel.setText(Integer.toString(fuelCost));
        Ship ship = game.getPlayer().getShip();
        if (player.getRegion() != nextRegion) {
            if (ship.getCurrentFuel() >= fuelCost && ship.getCurrentHealth() != 0) {
                travelButton.setEnabled(true);
                errorMessageLabel.setText("");
            } else {
                travelButton.setEnabled(false);
                if (ship.getCurrentHealth() == 0) {
                    errorMessageLabel.setText("Broken Ship");
                } else {
                    errorMessageLabel.setText("Insufficient Fuel");
                }
            }
            visitButton.setText("Back");
        } else {
            travelButton.setEnabled(false);
            visitButton.setText("Visit");
            errorMessageLabel.setText("");
        }
        int idx = Util.findIdxInArray(player.getRegion(), regions);
        for (int i = 0; i < regions.length; i++) {
            if (i == idx) {
                Stacking.setIcon(regionButtons[i], "shipGalaxy.png");
            } else {
                Stacking.setIcon(regionButtons[i], "galaxy.png");
            }
        }
        fuelValueLabel.setText(ship.getCurrentFuel() + " / " + ship.getFuelCapacity());
        hullValueLabel.setText(ship.getCurrentHealth() + " / " + ship.getHealthCapacity());
        renderUIStyle();
    }

    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, leftPanel, coordinatesPanel, rightPanel, shipPanel,
                regionPanel, bottomRightPanel, shipHullPanel, shipFuelPanel, hullProgress,
                fuelProgress, region0Panel, region1Panel, region2Panel, region3Panel, region4Panel,
                region6Panel, region8Panel, region5Panel, region7Panel, region9Panel, regionAPanel,
                regionBPanel, regionCPanel, regionDPanel, regionEPanel, regionFPanel);
        UIStyle.addDisplayTextColorStyle(xValueLabel, yValueLabel, distanceLabel,
                distanceValueLabel, shipLabel, regionLabel, regionValueLabel, techLevelLabel,
                techLevelValueLabel, coordinatesLabel, hullLabel, fuelLabel, region0Label,
                region1Label, region2Label, region3Label, region4Label, region6Label, region8Label,
                region5Label, region7Label, region9Label, regionALabel, regionBLabel, regionCLabel,
                regionDLabel, regionELabel, regionFLabel, fuelValueLabel, hullValueLabel,
                fuelCostLabel, fuelCostValueLabel);
        UIStyle.addErrorTextColorStyle(errorMessageLabel);
        UIStyle.addButtonStyle(travelButton, visitButton);
        UIStyle.addRegionButtonStyle(regionButtons);
        UIStyle.addPanelTranslucencyStyle(rightPanel);
        UIStyle.addProgressBarStyle(hullProgress, fuelProgress);
        Ship ship = game.getPlayer().getShip();
        UIStyle.repaintWithProgressBarStyle(fuelProgress,
                (double) ship.getCurrentFuel() / ship.getFuelCapacity(), UIStyle.FUEL_COLOR);
        UIStyle.repaintWithProgressBarStyle(hullProgress,
                (double) ship.getCurrentHealth() / ship.getHealthCapacity(), UIStyle.HULL_COLOR);
    }
}
