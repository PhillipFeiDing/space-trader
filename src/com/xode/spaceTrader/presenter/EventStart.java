package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.Stacking;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventStart extends PagePresenter {
    private JPanel panelMain;
    private JLabel secondLabel;
    private JLabel firstLabel;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JButton continueButton;
    private JPanel fourthPanel;
    private JLabel imageLabel;

    private Game game;

    private PagePresenter encounterBandit;
    private PagePresenter encounterPolice;
    private PagePresenter encounterTrader;

    public EventStart() {
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getNpc() instanceof Bandit) {
                    goToPage(encounterBandit);
                } else if (game.getNpc() instanceof Police) {
                    goToPage(encounterPolice);
                } else {
                    goToPage(encounterTrader);
                }
            }
        });
    }

    void setEncounterBandit(PagePresenter encounterBandit) {
        this.encounterBandit = encounterBandit;
    }

    void setEncounterPolice(PagePresenter encounterPolice) {
        this.encounterPolice = encounterPolice;
    }

    void setEncounterTrader(PagePresenter encounterTrader) {
        this.encounterTrader = encounterTrader;
    }

    @Override
    JPanel getPanelMain() {
        return panelMain;
    }

    @Override
    String getBackgroundImageName() {
        return "bigGalaxy.png";
    }

    void setGame(Game game) {
        this.game = game;
    }

    @Override
    void syncModelView() {
        secondLabel.setText(game.getNpc().getNpcType());
        NPC npc = game.getNpc();
        if (npc instanceof Bandit) {
            Stacking.setIcon(imageLabel, "bandit.png");
        } else if (npc instanceof Police) {
            Stacking.setIcon(imageLabel, "police.png");
        } else if (npc instanceof Trader) {
            Stacking.setIcon(imageLabel, "trader.png");
        }
        renderUIStyle();
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, firstPanel, secondPanel, thirdPanel, fourthPanel);
        UIStyle.addDisplayTextColorStyle(firstLabel, secondLabel);
        UIStyle.addButtonStyle(continueButton);
    }

}
