package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.util.UIStyle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends PagePresenter {
    private JPanel panelMain;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton newButton;
    private JButton loadButton;
    private JButton exitButton;
    private JLabel spaceTraderLabel;
    private JLabel welcomeLabel;
    private String backgroundImageURL =
           "background.jpeg";

    private PagePresenter configureSettings;

    Welcome() {
        newButton.addActionListener(e -> {
            goToPage(configureSettings);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    void setConfigureSettings(PagePresenter configureSettings) {
        this.configureSettings = configureSettings;
    }

    @Override
    JPanel getPanelMain() {
        return panelMain;
    }

    @Override
    String getBackgroundImageName() {
        return backgroundImageURL;
    }

    @Override
    void syncModelView() {
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, topPanel, bottomPanel);
        UIStyle.addButtonStyle(newButton, loadButton, exitButton);
        UIStyle.addDisplayTextColorStyle(spaceTraderLabel, welcomeLabel);
    }
}
