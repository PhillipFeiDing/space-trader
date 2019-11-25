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
import java.util.ArrayList;
import java.util.List;

public class EncounterTrader extends PagePresenter {
    private JPanel yourShipPanel;
    private JLabel yourShipLabel;
    private JPanel shipCargoPanel;
    private JPanel cargoPanel0;
    private JLabel cargoLabel0;
    private JLabel cargo0ValueLabel;
    private JPanel cargoPanel1;
    private JLabel cargoLabel1;
    private JLabel cargo1ValueLabe;
    private JPanel cargoPanel2;
    private JLabel cargoLabel2;
    private JLabel cargo2ValueLabe;
    private JPanel cargoPanel3;
    private JLabel cargoLabel3;
    private JLabel cargo3ValueLabe;
    private JPanel cargoPanel4;
    private JLabel cargoLabel4;
    private JLabel cargo4ValueLabe;
    private JPanel cargoPanel5;
    private JLabel cargoLabel5;
    private JLabel cargo5ValueLabe;
    private JPanel cargoPanel6;
    private JLabel cargoLabel6;
    private JLabel cargo6ValueLabe;
    private JPanel cargoPanel7;
    private JLabel cargoLabel7;
    private JLabel cargo7ValueLabe;
    private JPanel cargoPanel8;
    private JLabel cargoLabel8;
    private JLabel cargo8ValueLabe;
    private JPanel cargoPanel9;
    private JLabel cargoLabel9;
    private JLabel cargo9ValueLabe;
    private JPanel cargoPanel10;
    private JLabel cargoLabel10;
    private JLabel cargo10ValueLabe;
    private JPanel cargoPanel11;
    private JLabel cargoLabel11;
    private JLabel cargo11ValueLabe;
    private JPanel cargoPanel12;
    private JLabel cargoLabel12;
    private JLabel cargo12ValueLabe;
    private JPanel cargoPanel13;
    private JLabel cargoLabel13;
    private JLabel cargo13ValueLabe;
    private JPanel cargoPanel14;
    private JLabel cargoLabel14;
    private JLabel cargo14ValueLabe;
    private JPanel cargoPanel15;
    private JLabel cargoLabel15;
    private JLabel cargo15ValueLabe;
    private JPanel cargoPanel16;
    private JLabel cargoLabel16;
    private JLabel cargo16ValueLabe;
    private JPanel cargoPanel17;
    private JLabel cargoLabel17;
    private JLabel cargo17ValueLabe;
    private JPanel cargoPanel18;
    private JLabel cargoLabel18;
    private JLabel cargo18ValueLabe;
    private JPanel cargoPanel19;
    private JLabel cargoLabel19;
    private JLabel cargo19ValueLabe;
    private JLabel capacityLabel;
    private JButton negotiateButton;
    private JPanel traderShipPanel;
    private JLabel traderShipLabel;
    private JLabel goodsLabel;
    private JPanel traderItemPanel;
    private JPanel traderItemPanel3;
    private JLabel traderItemLabel3;
    private JPanel traderItemPanel4;
    private JLabel traderItemLabel4;
    private JPanel traderItemPanel5;
    private JLabel traderItemLabel5;
    private JPanel traderItemPanel6;
    private JLabel traderItemLabel6;
    private JPanel traderItemPanel7;
    private JLabel traderItemLabel7;
    private JPanel traderItemPanel8;
    private JLabel traderItemLabel8;
    private JPanel traderItemPanel9;
    private JLabel traderItemLabel9;
    private JPanel traderItemPanel2;
    private JLabel traderItemLabel2;
    private JPanel traderItemPanel1;
    private JLabel traderItemLabel1;
    private JPanel traderItemPanel0;
    private JLabel traderItemLabel0;
    private JButton buyButton;
    private JPanel panelMain;
    private JPanel infoPanel;
    private JPanel playerInfoPanel;
    private JLabel playerInfoLabel;
    private JLabel creditsLabel;
    private JLabel creditsValueLabel;
    private JLabel merchantLabel;
    private JLabel merchantValueLabel;
    private JButton leaveButton;
    private JPanel goodsInfoPanel;
    private JLabel traderDealLabel;
    private JLabel priceLabel;
    private JLabel priceValueLabel;
    private JLabel goodsNameLabel;
    private JLabel goodsNameValueLabel;
    private JLabel errorMessageLabel;
    private JLabel traderLabel;
    private JLabel fighterLabel;
    private JLabel fighterValueLabel;
    private JButton robButton;

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
        cargo0ValueLabel, cargo1ValueLabe, cargo2ValueLabe, cargo3ValueLabe, cargo4ValueLabe,
        cargo5ValueLabe, cargo6ValueLabe, cargo7ValueLabe, cargo8ValueLabe, cargo9ValueLabe,
        cargo10ValueLabe, cargo11ValueLabe, cargo12ValueLabe, cargo13ValueLabe, cargo14ValueLabe,
        cargo15ValueLabe, cargo16ValueLabe, cargo17ValueLabe, cargo18ValueLabe, cargo19ValueLabe
    };
    private final JPanel[] traderItemPanels = {
        traderItemPanel0, traderItemPanel1, traderItemPanel2, traderItemPanel3, traderItemPanel4,
        traderItemPanel5, traderItemPanel6, traderItemPanel7, traderItemPanel8, traderItemPanel9,
    };
    private final JLabel[] traderItemLabels = {
        traderItemLabel0, traderItemLabel1, traderItemLabel2, traderItemLabel3, traderItemLabel4,
        traderItemLabel5, traderItemLabel6, traderItemLabel7, traderItemLabel8, traderItemLabel9,
    };

    private Game game;
    private Integer focusIdx;
    private Boolean priceIncreased;
    private Boolean hasBought;

    private PagePresenter eventOutcome;
    private PagePresenter universeMap;

    public EncounterTrader() {
        for (JPanel panel: traderItemPanels) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (panel.isEnabled()) {
                        focusIdx = Util.findIdxInArray(panel, traderItemPanels);
                        syncModelView();
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (panel.isEnabled()) {
                        int idx = Util.findIdxInArray(panel, traderItemPanels);
                        setPriceLabels(idx);
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (panel.isEnabled()) {
                        syncModelView();
                    }
                }
            });
        }
        robButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Ship ship = player.getShip();
                boolean robSuccess = Calculator.getEventOutcome(player.getSkill(Skill.FIGHTER));
                Trader trader = (Trader) game.getNpc();
                if (robSuccess) {
                    game.setEventLog("You successfully robbed the trader.\n");
                    ArrayList<Goods> goods = trader.giveGoods();
                    for (Goods good: goods) {
                        if (ship.getNumCargoes() < ship.getCargoSpaceCapacity()) {
                            ship.addCargo(good, 0);
                            game.setEventLog(game.getEventLog()
                                    + "You got " + good.toString() + " for free.\n");
                        } else {
                            game.setEventLog(game.getEventLog()
                                    + "Your ship is now full and cannot carry another cargo.\n");
                        }
                    }
                } else {
                    game.setEventLog("You failed to rob the trader.\n");
                    int amount = trader.damageShip(ship);
                    game.setEventLog(game.getEventLog()
                            + "The trader fought back and damaged your ship by " + amount + ".\n");
                }
                int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                player.setRegion(nextRegion);
                game.setEventLog(game.getEventLog()
                        + "You continued your trip to " + nextRegion.getName() + ".\n");
                game.decKarma();
                goToPage(eventOutcome);
            }
        });
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                int nextRegionIdx = ((UniverseMap) universeMap).getNextRegionIdx();
                Region nextRegion = game.getUniverse().getRegions()[nextRegionIdx];
                player.setRegion(nextRegion);
                game.setEventLog("You said goodbye to the trader and continued to "
                        + nextRegion.getName() + ".\n");
                goToPage(eventOutcome);
            }
        });
        negotiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusIdx = -1;
                Player player = game.getPlayer();
                Trader trader = (Trader) game.getNpc();
                priceIncreased = !Calculator.getEventOutcome(player.getSkill(Skill.MERCHANT));
                if (priceIncreased) {
                    trader.increasePrice();
                } else {
                    trader.decreasePrice();
                }
                trader.setNegotiated(true);
                syncModelView();
            }
        });
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Trader trader = (Trader) game.getNpc();
                int price = trader.getPrices().get(focusIdx);
                Goods good = trader.sell(focusIdx);
                player.setCredits(player.getCredits() - price);

                Ship ship = player.getShip();
                ship.addCargo(good, price);

                focusIdx = -1;

                hasBought = true;

                syncModelView();
            }
        });
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
        Player player = game.getPlayer();
        Trader trader = (Trader) game.getNpc();

        Ship ship = player.getShip();
        capacityLabel.setText("Capacity: " + ship.getCargoSpaceCapacity());
        goodsLabel.setText("Remain: " + trader.getGoods().size());
        List<Goods> cargoes = ship.getCargoes();
        List<Integer> values = ship.getCargoValues();
        Util.setGoodsDisplay(shipCargoPanels, shipCargoLabels, cargoes, shipCargoValueLabels,
                values);

        List<Goods> traderGoods = trader.getGoods();
        Util.setGoodsDisplay(traderItemPanels, traderItemLabels, traderGoods, null, null);

        creditsValueLabel.setText("$" + player.getCredits().toString());
        merchantValueLabel.setText(player.getSkill(Skill.MERCHANT).toString());
        fighterValueLabel.setText(player.getSkill(Skill.FIGHTER).toString());
        setPriceLabels(focusIdx);

        if (focusIdx != -1) {
            Goods good = traderGoods.get(focusIdx);
            goodsNameValueLabel.setText(good.toString());
            if (player.getCredits() >= trader.getPrices().get(focusIdx)
                    && cargoes.size() < ship.getCargoSpaceCapacity()) {
                buyButton.setEnabled(true);
            } else {
                buyButton.setEnabled(false);
            }
        } else {
            buyButton.setEnabled(false);
        }

        if (trader.getNegotiated()) {
            negotiateButton.setEnabled(false);
        } else {
            negotiateButton.setEnabled(true);
        }

        if (!hasBought & ship.getNumCargoes() < ship.getCargoSpaceCapacity()) {
            robButton.setEnabled(true);
        } else {
            robButton.setEnabled(false);
        }

        renderUIStyle();
    }

    private void setPriceLabels(int idx) {
        if (idx == -1) {
            goodsNameValueLabel.setText("-");
            priceValueLabel.setText("-");
            if (priceIncreased == null) {
                errorMessageLabel.setText(" ");
            } else if (priceIncreased) {
                errorMessageLabel.setText("Price Increased");
            } else {
                errorMessageLabel.setText("Price Decreased");
            }
            return;
        }
        Trader trader = (Trader) game.getNpc();
        goodsNameValueLabel.setText(trader.getGoods().get(idx).toString());
        int price = trader.getPrices().get(idx);
        priceValueLabel.setText("$" + price);
        Player player = game.getPlayer();
        Ship ship = game.getPlayer().getShip();
        if (price > player.getCredits()) {
            errorMessageLabel.setText("Credits Not Enough");
        } else if (ship.getCargoes().size() >= ship.getCargoSpaceCapacity()) {
            errorMessageLabel.setText("Space Full");
        } else {
            errorMessageLabel.setText(" ");
        }
    }

    JPanel getPanelMain() {
        return this.panelMain;
    }

    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    @Override
    void reset() {
        focusIdx = -1;
        priceIncreased = null;
        hasBought = false;
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, infoPanel, traderShipPanel, yourShipPanel,
                playerInfoPanel, goodsInfoPanel, traderItemPanel, shipCargoPanel);
        UIStyle.addTransparencyStyle(shipCargoPanels);
        UIStyle.addTransparencyStyle(traderItemPanels);
        UIStyle.addDisplayTextColorStyle(playerInfoLabel, creditsLabel, creditsValueLabel,
                merchantLabel, merchantValueLabel, fighterLabel, fighterValueLabel,
                traderDealLabel, priceLabel, priceValueLabel, traderShipLabel,
                goodsLabel, goodsNameLabel, goodsNameValueLabel, capacityLabel, yourShipLabel,
                traderLabel);
        UIStyle.addDisplayTextColorStyle(shipCargoLabels);
        UIStyle.addDisplayTextColorStyle(traderItemLabels);
        UIStyle.addDisplayTextColorStyle(shipCargoValueLabels);
        UIStyle.addErrorTextColorStyle(errorMessageLabel);
        UIStyle.addButtonStyle(negotiateButton, robButton, buyButton, leaveButton);
        UIStyle.addPanelTranslucencyStyle(shipCargoPanel, traderItemPanel, playerInfoPanel,
                goodsInfoPanel
        );
        UIStyle.addHoverHandCursorStyle(traderItemPanels);
        UIStyle.addGoodsPanelStyle(game.getPlayer().getShip().getCargoSpaceCapacity(),
                shipCargoPanels);
        UIStyle.addGoodsPanelStyle(game.getPlayer().getRegion().getMarket().getGoods().size(),
                traderItemPanels);
        if (focusIdx != -1) {
            JPanel focusPanel = traderItemPanels[focusIdx];
            UIStyle.addGoodsPanelFocusStyle(focusPanel);
        }
    }

}
