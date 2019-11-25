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

public class RegionMarket extends PagePresenter {
    private JPanel panelMain;
    private JPanel infoPanel;
    private JPanel marketPanel;
    private JPanel shipPanel;
    private JPanel playerInfoPanel;
    private JPanel goodsInfoPanel;
    private JPanel marketItemPanel;
    private JPanel marketItemPanel3;
    private JPanel marketItemPanel0;
    private JPanel marketItemPanel1;
    private JPanel marketItemPanel2;
    private JPanel marketItemPanel4;
    private JPanel marketItemPanel5;
    private JPanel marketItemPanel6;
    private JPanel marketItemPanel7;
    private JPanel marketItemPanel8;
    private JPanel marketItemPanel9;
    private JPanel marketItemPanel10;
    private JPanel marketItemPanel11;
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
    private JPanel shipCargoPanel;
    private JLabel playerInfoLabel;
    private JLabel creditsLabel;
    private JLabel creditsValueLabel;
    private JLabel merchantLabel;
    private JLabel merchantValueLabel;
    private JLabel goodsInfoLabel;
    private JLabel originalPriceLabel;
    private JLabel originalPriceValueLabel;
    private JLabel dealPriceLabel;
    private JLabel dealPriceValueLabel;
    private JLabel regionNameLabel;
    private JLabel techLevelValueLabel;
    private JLabel marketItemLabel3;
    private JLabel marketItemLabel0;
    private JLabel marketItemLabel1;
    private JLabel marketItemLabel2;
    private JLabel marketItemLabel4;
    private JLabel marketItemLabel5;
    private JLabel marketItemLabel6;
    private JLabel marketItemLabel7;
    private JLabel marketItemLabel8;
    private JLabel marketItemLabel9;
    private JLabel marketItemLabel10;
    private JLabel marketItemLabel11;
    private JLabel cargoLabel0;
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
    private JLabel goodsNameLabel;
    private JLabel goodsNameValueLabel;
    private JLabel capacityLabel;
    private JLabel shipLabel;
    private JLabel marketLabel;
    private JLabel cargo0ValueLabel;
    private JLabel cargo1ValueLabe;
    private JLabel cargo2ValueLabe;
    private JLabel cargo3ValueLabe;
    private JLabel cargo4ValueLabe;
    private JLabel cargo5ValueLabe;
    private JLabel cargo6ValueLabe;
    private JLabel cargo7ValueLabe;
    private JLabel cargo8ValueLabe;
    private JLabel cargo9ValueLabe;
    private JLabel cargo10ValueLabe;
    private JLabel cargo11ValueLabe;
    private JLabel cargo12ValueLabe;
    private JLabel cargo13ValueLabe;
    private JLabel cargo14ValueLabe;
    private JLabel cargo15ValueLabe;
    private JLabel cargo16ValueLabe;
    private JLabel cargo17ValueLabe;
    private JLabel cargo18ValueLabe;
    private JLabel cargo19ValueLabe;
    private JButton sellButton;
    private JButton buyButton;
    private JButton leaveButton;
    private JLabel errorMessageLabel;

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
    private final JPanel[] marketItemPanels = {
        marketItemPanel0, marketItemPanel1, marketItemPanel2, marketItemPanel3, marketItemPanel4,
        marketItemPanel5, marketItemPanel6, marketItemPanel7, marketItemPanel8, marketItemPanel9,
        marketItemPanel10, marketItemPanel11
    };
    private final JLabel[] marketItemLabels = {
        marketItemLabel0, marketItemLabel1, marketItemLabel2, marketItemLabel3, marketItemLabel4,
        marketItemLabel5, marketItemLabel6, marketItemLabel7, marketItemLabel8, marketItemLabel9,
        marketItemLabel10, marketItemLabel11
    };

    private PagePresenter currentRegion;
    private PagePresenter gameWin;

    private Game game;
    private Boolean focusMarket;
    private Integer focusIdx;

    public RegionMarket() {
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPage(currentRegion);
            }
        });

        focusMarket = null;
        focusIdx = null;

        for (JPanel panel: marketItemPanels) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (panel.isEnabled()) {
                        focusMarket = true;
                        focusIdx = Util.findIdxInArray(panel, marketItemPanels);
                        syncModelView();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (panel.isEnabled()) {
                        int idx = Util.findIdxInArray(panel, marketItemPanels);
                        Goods good = game.getPlayer().getRegion().getMarket().getGoods().get(idx);
                        setPriceLabels(good, true);
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

        for (JPanel panel: shipCargoPanels) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (panel.isEnabled()) {
                        focusMarket = false;
                        focusIdx = Util.findIdxInArray(panel, shipCargoPanels);
                        syncModelView();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (panel.isEnabled()) {
                        int idx = Util.findIdxInArray(panel, shipCargoPanels);
                        Goods good = game.getPlayer().getShip().getCargoes().get(idx);
                        setPriceLabels(good, false);
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

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Market market = player.getRegion().getMarket();
                List<Goods> marketGoods = market.getGoods();
                Goods good = marketGoods.get(focusIdx);
                int price = Calculator.getBuyDealPrice(market.getPrice(good));
                player.setCredits(player.getCredits() - price);

                Ship ship = player.getShip();
                ship.addCargo(good, price);

                if (good.equals(Goods.UNIVERSE)) {
                    goToPage(gameWin);
                }

                syncModelView();
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = game.getPlayer();
                Market market = player.getRegion().getMarket();

                Ship ship = player.getShip();
                Goods good = ship.removeCargo(focusIdx);

                int price = Calculator.getSellDealPrice(market.getPrice(good));
                player.setCredits(player.getCredits() + price);

                reset();
                syncModelView();
            }
        });
    }


    void setGame(Game game) {
        this.game = game;
    }

    void setCurrentRegion(PagePresenter currentRegion) {
        this.currentRegion = currentRegion;
    }

    void setGameWin(PagePresenter gameWin) {
        this.gameWin = gameWin;
    }

    JPanel getPanelMain() {
        return panelMain;
    }

    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    void syncModelView() {
        Player player = game.getPlayer();

        Ship ship = player.getShip();
        capacityLabel.setText("Capacity: " + ship.getCargoSpaceCapacity());
        regionNameLabel.setText(player.getRegion().getName());
        techLevelValueLabel.setText(player.getRegion().getTechLevel().toString());
        List<Goods> cargoes = ship.getCargoes();
        List<Integer> values = ship.getCargoValues();
        Util.setGoodsDisplay(shipCargoPanels, shipCargoLabels, cargoes, shipCargoValueLabels,
                values);

        Market market = player.getRegion().getMarket();
        List<Goods> marketGoods = market.getGoods();
        Util.setGoodsDisplay(marketItemPanels, marketItemLabels, marketGoods, null, null);

        creditsValueLabel.setText("$" + player.getCredits().toString());
        merchantValueLabel.setText(player.getSkill(Skill.MERCHANT).toString());

        if (focusMarket != null) {
            Goods good = focusMarket ? marketGoods.get(focusIdx) : cargoes.get(focusIdx);
            goodsNameValueLabel.setText(good.toString());
            if (focusMarket) {
                int dealPrice = setPriceLabels(good, true);
                if (player.getCredits() >= dealPrice
                        && cargoes.size() < ship.getCargoSpaceCapacity()) {
                    buyButton.setEnabled(true);
                } else {
                    buyButton.setEnabled(false);
                }
                sellButton.setEnabled(false);
            } else {
                setPriceLabels(good, false);
                sellButton.setEnabled(true);
                buyButton.setEnabled(false);
            }
        } else {
            buyButton.setEnabled(false);
            sellButton.setEnabled(false);
            setPriceLabels(null, false);
        }

        renderUIStyle();
    }

    private int setPriceLabels(Goods good, boolean isBuy) {
        if (good == null) {
            goodsNameValueLabel.setText("-");
            originalPriceValueLabel.setText("-");
            dealPriceValueLabel.setText("-");
            errorMessageLabel.setText(" ");
            return 0;
        }
        goodsNameValueLabel.setText(good.toString());
        Market market = game.getPlayer().getRegion().getMarket();
        int rawPrice = market.getPrice(good);
        int dealPrice;
        if (isBuy) {
            originalPriceValueLabel.setText("$" + Calculator.getBuyOriginalPrice(rawPrice));
            dealPrice = Calculator.getBuyDealPrice(rawPrice);
            Player player = game.getPlayer();
            Ship ship = player.getShip();
            if (dealPrice > game.getPlayer().getCredits()) {
                errorMessageLabel.setText("Credits Not Enough");
            } else if (ship.getCargoes().size() >= ship.getCargoSpaceCapacity()) {
                errorMessageLabel.setText("Space Full");
            } else {
                errorMessageLabel.setText(" ");
            }
        } else {
            originalPriceValueLabel.setText("$" + Calculator.getSellOriginalPrice(rawPrice));
            dealPrice = Calculator.getSellDealPrice(rawPrice);
            errorMessageLabel.setText(" ");
        }
        dealPriceValueLabel.setText("$" + dealPrice);
        return dealPrice;
    }

    @Override
    void reset() {
        focusIdx = null;
        focusMarket = null;
    }

    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, infoPanel, marketPanel, shipPanel, playerInfoPanel,
                goodsInfoPanel, marketItemPanel, shipCargoPanel);
        UIStyle.addTransparencyStyle(shipCargoPanels);
        UIStyle.addTransparencyStyle(marketItemPanels);
        UIStyle.addDisplayTextColorStyle(playerInfoLabel, creditsLabel, creditsValueLabel,
                merchantLabel, merchantValueLabel, goodsInfoLabel, originalPriceLabel,
                originalPriceValueLabel, dealPriceLabel, dealPriceValueLabel, regionNameLabel,
                techLevelValueLabel, goodsNameLabel, goodsNameValueLabel, capacityLabel, shipLabel,
                marketLabel);
        UIStyle.addDisplayTextColorStyle(shipCargoLabels);
        UIStyle.addDisplayTextColorStyle(marketItemLabels);
        UIStyle.addDisplayTextColorStyle(shipCargoValueLabels);
        UIStyle.addErrorTextColorStyle(errorMessageLabel);
        UIStyle.addButtonStyle(sellButton, buyButton, leaveButton);
        UIStyle.addPanelTranslucencyStyle(shipCargoPanel, marketItemPanel, playerInfoPanel,
                goodsInfoPanel
        );
        UIStyle.addHoverHandCursorStyle(shipCargoPanels);
        UIStyle.addHoverHandCursorStyle(marketItemPanels);
        UIStyle.addGoodsPanelStyle(game.getPlayer().getShip().getCargoSpaceCapacity(),
                shipCargoPanels);
        UIStyle.addGoodsPanelStyle(game.getPlayer().getRegion().getMarket().getGoods().size(),
                marketItemPanels);
        if (focusMarket != null) {
            JPanel focusPanel = focusMarket ? marketItemPanels[focusIdx]
                    : shipCargoPanels[focusIdx];
            UIStyle.addGoodsPanelFocusStyle(focusPanel);
        }
    }
}
