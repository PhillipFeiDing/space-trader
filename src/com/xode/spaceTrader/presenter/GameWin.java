package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;

public class GameWin extends PagePresenter {
    private JButton restartButton;
    private JButton exitButton;
    private JPanel panelMain;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel winLabel;
    private JLabel universeLabel;

    public GameWin() {
        restartButton.addActionListener(e -> {
            Main.restart();
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    JPanel getPanelMain() {
        return panelMain;
    }

    String getBackgroundImageName() {
        return "space.jpg";
    }

    void syncModelView() {
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, topPanel, bottomPanel);
        UIStyle.addButtonStyle(restartButton, exitButton);
        UIStyle.addDisplayTextColorStyle(winLabel, universeLabel);
    }
}

