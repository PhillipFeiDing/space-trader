package com.xode.spaceTrader.presenter;


import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;

public class GameOver extends PagePresenter {
    private JPanel panelMain;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton restartButton;
    private JButton exitButton;
    private JLabel explodeLabel;
    private JLabel overLabel;

    public GameOver() {
        restartButton.addActionListener(e -> Main.restart());
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
        UIStyle.addDisplayTextColorStyle(explodeLabel, overLabel);
    }
}
