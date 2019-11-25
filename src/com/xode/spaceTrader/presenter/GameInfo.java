package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.UIStyle;
import com.xode.spaceTrader.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameInfo extends PagePresenter {
    private JButton backButton;
    private JLabel playerLabel;
    private JLabel nameLabel;
    private JLabel creditsLabel;
    private JLabel gameDifficultyLabel;
    private JLabel skillsLabel;
    private JLabel pilotLabel;
    private JLabel fighterLabel;
    private JLabel merchantLabel;
    private JLabel engineerLabel;
    private JLabel nameValueLabel;
    private JLabel creditsValueLabel;
    private JLabel gameDifficultyValueLabel;
    private JLabel pilotValueLabel;
    private JLabel fighterValueLabel;
    private JLabel merchantValueLabel;
    private JLabel engineerValueLabel;
    private JLabel shipLabel;
    private JLabel typeLabel;
    private JLabel healthLabel;
    private JLabel fuelLabel;
    private JLabel typeValueLabel;
    private JLabel healthValueLabel;
    private JLabel fuelValueLabel;
    private JLabel messageLabel;
    private JPanel panelMain;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel playerPanel;
    private JPanel shipPanel;
    private JPanel healthPanel;
    private JPanel fuelPanel;
    private JPanel cargoesPanel;
    private JPanel topPanel;
    private JPanel skillsPanel;
    private JLabel capacityLabel;
    private JLabel capacityValueLabel;
    private JPanel cargoPanel0;
    private JPanel cargoPanel1;
    private JPanel cargoPanel2;
    private JPanel cargoPanel3;
    private JPanel cargoPanel4;
    private JPanel cargoPanel5;
    private JPanel cargoPanel6;
    private JPanel cargoPanel7;
    private JPanel cargoPanel8;
    private JPanel cargoPanel9;
    private JPanel cargoPanel10;
    private JPanel cargoPanel11;
    private JPanel cargoPanel12;
    private JPanel cargoPanel13;
    private JPanel cargoPanel14;
    private JPanel cargoPanel15;
    private JPanel cargoPanel16;
    private JPanel cargoPanel17;
    private JPanel cargoPanel18;
    private JPanel cargoPanel19;
    private JLabel cargoesLabel;
    private JLabel cargoLabel0;
    private JLabel cargo0ValueLabel;
    private JLabel cargoLabel1;
    private JLabel cargoLabel2;
    private JLabel cargoLabel3;
    private JLabel cargoLabel4;
    private JLabel cargoLabel5;
    private JLabel cargoLabel6;
    private JLabel cargoLabel7;
    private JLabel cargoLabel8;
    private JLabel cargoLabel9;
    private JLabel cargoLabel10;
    private JLabel cargoLabel11;
    private JLabel cargoLabel12;
    private JLabel cargoLabel13;
    private JLabel cargoLabel14;
    private JLabel cargoLabel15;
    private JLabel cargoLabel16;
    private JLabel cargoLabel17;
    private JLabel cargoLabel18;
    private JLabel cargoLabel19;
    private JLabel cargo1ValueLabel;
    private JLabel cargo2ValueLabel;
    private JLabel cargo3ValueLabel;
    private JLabel cargo4ValueLabel;
    private JLabel cargo5ValueLabel;
    private JLabel cargo6ValueLabel;
    private JLabel cargo7ValueLabel;
    private JLabel cargo8ValueLabel;
    private JLabel cargo9ValueLabel;
    private JLabel cargo10ValueLabel;
    private JLabel cargo11ValueLabel;
    private JLabel cargo12ValueLabel;
    private JLabel cargo13ValueLabel;
    private JLabel cargo14ValueLabel;
    private JLabel cargo15ValueLabel;
    private JLabel cargo16ValueLabel;
    private JLabel cargo17ValueLabel;
    private JLabel cargo18ValueLabel;
    private JLabel cargo19ValueLabel;
    private JLabel karmaValueLabel;
    private JLabel karmaLabel;

    private final JComponent[] infoObjs = {
        nameLabel, nameValueLabel, creditsLabel, creditsValueLabel, gameDifficultyLabel,
        gameDifficultyValueLabel, pilotLabel, pilotValueLabel, fighterLabel, fighterValueLabel,
        merchantLabel, merchantValueLabel, engineerLabel, engineerValueLabel, capacityLabel,
        capacityValueLabel, typeLabel, typeValueLabel, healthLabel, healthPanel, fuelLabel,
        fuelPanel, cargoesLabel, cargoesPanel, karmaLabel, karmaValueLabel
    };

    private final JPanel[] shipCargoPanels = {
        cargoPanel0, cargoPanel1, cargoPanel2, cargoPanel3, cargoPanel4, cargoPanel5, cargoPanel6,
        cargoPanel7, cargoPanel8, cargoPanel9, cargoPanel10, cargoPanel11, cargoPanel12,
        cargoPanel13, cargoPanel14, cargoPanel15, cargoPanel16, cargoPanel17, cargoPanel18,
        cargoPanel19
    };

    private final JLabel[] shipCargoLabels = {
        cargoLabel0, cargoLabel1, cargoLabel2, cargoLabel3, cargoLabel4, cargoLabel5, cargoLabel6,
        cargoLabel7, cargoLabel8, cargoLabel9, cargoLabel10, cargoLabel11, cargoLabel12,
        cargoLabel13, cargoLabel14, cargoLabel15, cargoLabel16, cargoLabel17, cargoLabel18,
        cargoLabel19
    };

    private final JLabel[] shipCargoValueLabels = {
        cargo0ValueLabel, cargo1ValueLabel, cargo2ValueLabel, cargo3ValueLabel, cargo4ValueLabel,
        cargo5ValueLabel, cargo6ValueLabel, cargo7ValueLabel, cargo8ValueLabel, cargo9ValueLabel,
        cargo10ValueLabel, cargo11ValueLabel, cargo12ValueLabel, cargo13ValueLabel,
        cargo14ValueLabel, cargo15ValueLabel, cargo16ValueLabel, cargo17ValueLabel,
        cargo18ValueLabel, cargo19ValueLabel
    };

    private Game game;

    private PagePresenter returnPage;
    private Integer focus;

    public GameInfo() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(returnPage);
            }
        });
        for (JComponent infoObj: infoObjs) {
            infoObj.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    focus = Util.findIdxInArray(infoObj, infoObjs);
                    setInfoMessage();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    focus = null;
                    setInfoMessage();
                }
            });
        }
    }

    @Override
    JPanel getPanelMain() {
        return panelMain;
    }

    @Override
    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    void setReturnPage(PagePresenter returnPage) {
        this.returnPage = returnPage;
    }

    void setGame(Game game) {
        this.game = game;
    }

    private void setInfoMessage() {
        Player player = game.getPlayer();
        Ship ship = player.getShip();
        if (focus == null) {
            messageLabel.setText(" ");
        } else if (focus == 0 || focus == 1) {
            messageLabel.setText("Your name.");
        } else if (focus == 2 || focus == 3) {
            messageLabel.setText("For buying and selling things in universe.");
        } else if (focus == 4 || focus == 5) {
            messageLabel.setText("Decides game parameter settings.");
        } else if (focus == 6 || focus == 7) {
            messageLabel.setText(String.format("Save %d%% fuel each travel.",
                    Calculator.getFuelDiscount()));
        } else if (focus == 8 || focus == 9) {
            messageLabel.setText("Decides the chance you fight against enemies!");
        } else if (focus == 10 || focus == 11) {
            messageLabel.setText(String.format(
                    "Buy with %d%% discount and sell for up to %d%% extra.",
                    Calculator.getPriceDiscount(), Calculator.getPriceDiscount()));
        } else if (focus == 12 || focus == 13) {
            messageLabel.setText("Higher skill point makes cheaper to repair your ship.");
        } else if (focus == 14 || focus == 15) {
            messageLabel.setText("Maximum number of cargoes your ship can host.");
        } else if (focus == 16 || focus == 17) {
            messageLabel.setText(String.format("Your ship type is worth $%d.",
                    ship.getType().getValue()));
        } else if (focus == 18 || focus == 19) {
            messageLabel.setText("Defend yourself against attacks!");
        } else if (focus == 20 || focus == 21) {
            messageLabel.setText("Make sure you have enough fuel before travel!");
        } else if (focus == 22 || focus == 23) {
            messageLabel.setText("View your cargoes and prices purchased.");
        } else if (focus == 24 || focus == 25) {
            messageLabel.setText("Your reputation across the universe.");
        }
    }

    @Override
    void reset() {
        focus = null;
    }

    @Override
    void redirect(PagePresenter previousPage) {
        this.returnPage = previousPage;
    }

    @Override
    void syncModelView() {
        Player player = game.getPlayer();
        nameValueLabel.setText(player.getName());
        creditsValueLabel.setText("$" + player.getCredits());
        gameDifficultyValueLabel.setText(game.getDifficulty().toString());
        karmaValueLabel.setText(game.getKarma().toString());
        pilotValueLabel.setText(player.getSkill(Skill.PILOT).toString());
        fighterValueLabel.setText(player.getSkill(Skill.FIGHTER).toString());
        merchantValueLabel.setText(player.getSkill(Skill.MERCHANT).toString());
        engineerValueLabel.setText(player.getSkill(Skill.ENGINEER).toString());
        messageLabel.setText(" ");

        Ship ship = player.getShip();
        capacityValueLabel.setText(Integer.toString(ship.getCargoSpaceCapacity()));
        typeValueLabel.setText(ship.getType().toString());
        fuelValueLabel.setText(ship.getCurrentFuel() + " / " + ship.getFuelCapacity());
        healthValueLabel.setText(ship.getCurrentHealth() + " / " + ship.getHealthCapacity());

        List<Goods> cargoes = ship.getCargoes();
        List<Integer> values = ship.getCargoValues();
        Util.setGoodsDisplay(shipCargoPanels, shipCargoLabels, cargoes, shipCargoValueLabels,
                values);

        renderUIStyle();
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, leftPanel, rightPanel, playerPanel, shipPanel,
                healthPanel, fuelPanel, cargoesPanel, topPanel, skillsPanel);
        UIStyle.addTransparencyStyle(shipCargoPanels);
        UIStyle.addDisplayTextColorStyle(playerLabel, nameLabel, creditsLabel, gameDifficultyLabel,
                skillsLabel, pilotLabel, fighterLabel, merchantLabel, engineerLabel, nameValueLabel,
                creditsValueLabel, gameDifficultyValueLabel, pilotValueLabel, fighterValueLabel,
                merchantValueLabel, engineerValueLabel, shipLabel, typeLabel, healthLabel,
                fuelLabel, typeValueLabel, healthValueLabel, cargoesLabel,
                fuelValueLabel, messageLabel, capacityLabel, capacityValueLabel,
                karmaLabel, karmaValueLabel);
        UIStyle.addDisplayTextColorStyle(shipCargoLabels);
        UIStyle.addDisplayTextColorStyle(shipCargoValueLabels);
        UIStyle.addButtonStyle(backButton);
        UIStyle.addProgressBarStyle(fuelPanel, healthPanel);
        Ship ship = game.getPlayer().getShip();
        UIStyle.repaintWithProgressBarStyle(fuelPanel,
                (double) ship.getCurrentFuel() / ship.getFuelCapacity(), UIStyle.FUEL_COLOR);
        UIStyle.repaintWithProgressBarStyle(healthPanel,
                (double) ship.getCurrentHealth() / ship.getHealthCapacity(), UIStyle.HULL_COLOR);
        UIStyle.addPanelTranslucencyStyle(leftPanel, rightPanel);
        UIStyle.addGoodsPanelStyle(game.getPlayer().getShip().getCargoSpaceCapacity(),
                shipCargoPanels);
    }
}
