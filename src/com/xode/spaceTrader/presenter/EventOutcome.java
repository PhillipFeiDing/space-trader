package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.model.Ship;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventOutcome extends PagePresenter {
    private JPanel panelMain;
    private JButton okayButton;
    private JTextArea messageTextArea;
    private JPanel messagePanel;
    private JLabel outcomeLabel;

    private Game game;

    private PagePresenter universeMap;
    private PagePresenter gameOver;

    public EventOutcome() {
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ship ship = game.getPlayer().getShip();
                if (ship.getCurrentHealth() == 0) {
                    goToPage(gameOver);
                } else {
                    goToPage(universeMap);
                }
            }
        });
    }

    void setUniverseMap(PagePresenter universeMap) {
        this.universeMap = universeMap;
    }

    void setGameOver(PagePresenter gameOver) {
        this.gameOver = gameOver;
    }

    void setGame(Game game) {
        this.game = game;
    }

    void syncModelView() {
        messageTextArea.setText(game.getEventLog());
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
        UIStyle.addTransparencyStyle(panelMain, messagePanel);
        UIStyle.addButtonStyle(okayButton);
        UIStyle.addPanelTranslucencyStyle(messagePanel);
        UIStyle.addTextDisplayTranslucentStyle(messageTextArea);
        UIStyle.addDisplayTextColorStyle(outcomeLabel);
    }

}
