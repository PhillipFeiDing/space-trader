package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.model.Ship;
import com.xode.spaceTrader.model.Skill;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shipyard extends PagePresenter {
    private JPanel panelMain;
    private JTextField wantRepairTextField;
    private JButton repairButton;
    private JTextField wantRefuelTextField;
    private JButton refuelButton;
    private JLabel shipYardLabel;
    private JPanel creditSkillPanel;
    private JLabel creditLabel;
    private JLabel creditAmountLabel;
    private JLabel engineerSkillLabel;
    private JLabel engineerSkillAmountLabel;
    private JLabel shipLabel;
    private JLabel healthLabel;
    private JLabel healthProgressLabel;
    private JPanel healthProgressPanel;
    private JPanel healthOptionPanel;
    private JLabel healthPriceLabel;
    private JPanel fuelPanel;
    private JLabel fuelLabel;
    private JPanel fuelProgressPanel;
    private JLabel fuelProgressLabel;
    private JPanel fuelOptionPanel;
    private JPanel healthPanel;
    private JLabel fuelPriceLabel;
    private JLabel errorMessageLabel;
    private JButton okayButton;

    private Game game;

    private PagePresenter currentRegion;

    private static final double CREDIT_PER_HEALTH = 4.00;
    private static final double CREDIT_PER_FUEL = 0.05;
    private double actualHealthPrice;

    public Shipyard() {
        repairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairHealth(game);
                syncModelView();
            }
        });

        refuelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refuel(game);
                syncModelView();
            }
        });

        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(currentRegion);
            }
        });

        errorMessageLabel.setText(" ");
    }

    private void repairHealth(Game game) {
        try {
            int currentHealth = game.getPlayer().getShip().getCurrentHealth();
            int healthCapacity = game.getPlayer().getShip().getHealthCapacity();
            int amountOfHealth = Integer.parseInt(wantRepairTextField.getText());
            int creditNeeded = (int) Math.ceil(amountOfHealth * actualHealthPrice);

            if (amountOfHealth <= 0) {
                throw new IllegalArgumentException("You should input a positive integer.");
            }
            if (amountOfHealth > healthCapacity - currentHealth) {
                throw new IllegalArgumentException(
                        "The amount has exceeded the remaining health capacity!");
            }
            if (creditNeeded > game.getPlayer().getCredits()) {
                throw new IllegalArgumentException("Your credit is not enough!");
            }
            game.getPlayer().getShip().setCurrentHealth(currentHealth + amountOfHealth);
            game.getPlayer().setCredits(game.getPlayer().getCredits() - creditNeeded);
            errorMessageLabel.setText(" ");
        } catch (NumberFormatException n) {
            errorMessageLabel.setText("You should input an integer!");
        } catch (IllegalArgumentException e) {
            errorMessageLabel.setText(e.getMessage());
        }
    }

    private void refuel(Game game) {
        try {
            int currentFuel = game.getPlayer().getShip().getCurrentFuel();
            int fuelCapacity = game.getPlayer().getShip().getFuelCapacity();
            int amountOfFuel = Integer.parseInt(wantRefuelTextField.getText());
            int creditNeeded = (int) Math.ceil(amountOfFuel * CREDIT_PER_FUEL);
            if (amountOfFuel <= 0) {
                throw new IllegalArgumentException("You should input a positive integer.");
            }
            if (amountOfFuel > fuelCapacity - currentFuel) {
                throw new IllegalArgumentException(
                        "The amount has exceeded the remaining fuel capacity!");
            }
            if (creditNeeded > game.getPlayer().getCredits()) {
                throw new IllegalArgumentException("Your credit is not enough!");
            }
            game.getPlayer().getShip().setCurrentFuel(currentFuel + amountOfFuel);
            game.getPlayer().setCredits(game.getPlayer().getCredits() - creditNeeded);
            errorMessageLabel.setText(" ");
        } catch (NumberFormatException n) {
            errorMessageLabel.setText("You should input an integer!");
        } catch (IllegalArgumentException e) {
            errorMessageLabel.setText(e.getMessage());
        }
    }

    void setGame(Game game) {
        this.game = game;
    }

    void setCurrentRegion(PagePresenter currentRegion) {
        this.currentRegion = currentRegion;
    }


    @Override
    void syncModelView() {
        Ship ship = game.getPlayer().getShip();
        actualHealthPrice = CREDIT_PER_HEALTH
                * (1 - (game.getPlayer().getSkill(Skill.ENGINEER) * 0.06));
        healthPriceLabel.setText("$" + String.format("%.2f", actualHealthPrice) + " / Health");
        fuelPriceLabel.setText("$" + String.format("%.2f", (double) CREDIT_PER_FUEL) + " / Fuel");
        creditAmountLabel.setText("$" + game.getPlayer().getCredits());
        engineerSkillAmountLabel.setText(Integer.toString(
                game.getPlayer().getSkill(Skill.ENGINEER)));
        fuelProgressLabel.setText(ship.getCurrentFuel() + " / " + ship.getFuelCapacity());
        healthProgressLabel.setText(ship.getCurrentHealth() + " / " + ship.getHealthCapacity());
        wantRefuelTextField.setText("0");
        wantRepairTextField.setText("0");
        renderUIStyle();
    }

    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, creditSkillPanel, healthProgressPanel,
                healthOptionPanel, fuelPanel, fuelProgressPanel, fuelOptionPanel, healthPanel);
        UIStyle.addDisplayTextColorStyle(shipYardLabel, creditLabel, creditAmountLabel,
                engineerSkillLabel, engineerSkillAmountLabel, shipLabel, healthLabel,
                healthProgressLabel, healthPriceLabel, fuelLabel, fuelProgressLabel,
                fuelPriceLabel, errorMessageLabel);
        UIStyle.addErrorTextColorStyle(errorMessageLabel);
        UIStyle.addButtonStyle(repairButton, refuelButton, okayButton);
        UIStyle.addProgressBarStyle(healthProgressPanel, fuelProgressPanel);
        UIStyle.addTextDisplayTranslucentStyle(wantRefuelTextField, wantRepairTextField);
        Ship ship = game.getPlayer().getShip();
        UIStyle.repaintWithProgressBarStyle(fuelProgressPanel,
                (double) ship.getCurrentFuel() / ship.getFuelCapacity(), UIStyle.FUEL_COLOR);
        UIStyle.repaintWithProgressBarStyle(healthProgressPanel,
                (double) ship.getCurrentHealth() / ship.getHealthCapacity(), UIStyle.HULL_COLOR);
    }

    @Override
    void reset() {
        errorMessageLabel.setText(" ");
    }

    @Override
    JPanel getPanelMain() {
        return this.panelMain;
    }

    @Override
    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }
}
