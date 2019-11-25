package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.model.Player;
import com.xode.spaceTrader.model.Skill;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfirmSettings extends PagePresenter {
    private JPanel panelMain;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JPanel midTopPanel;
    private JPanel midMidPanel;
    private JPanel midBottomPanel;
    private JLabel engineerLabel;
    private JLabel engineerValueLabel;
    private JLabel merchantLabel;
    private JLabel merchantValueLabel;
    private JLabel fighterLabel;
    private JLabel fighterValueLabel;
    private JLabel pilotLabel;
    private JLabel pilotValueLabel;
    private JLabel skillsLabel;
    private JLabel creditsLabel;
    private JLabel creditsValueLabel;
    private JLabel nameLabel;
    private JLabel nameValueLabel;
    private JLabel settingsLabel;
    private JButton backButton;
    private JButton okayButton;

    private Game game;

    private PagePresenter configureSettings;
    private PagePresenter currentRegion;

    private String backgroundImageName =
                  "background.jpeg";

    ConfirmSettings() {
        okayButton.addActionListener(e -> {
            goToPage(currentRegion);
        });

        backButton.addActionListener(e -> {
            goToPage(configureSettings);
        });
    }

    void setConfigureSettings(PagePresenter configureSettings) {
        this.configureSettings = configureSettings;
    }

    void setCurrentRegion(PagePresenter currentRegion) {
        this.currentRegion = currentRegion;
    }

    void setGame(Game game) {
        this.game = game;
    }

    @Override
    JPanel getPanelMain() {
        return panelMain;
    }

    @Override
    String getBackgroundImageName() {
        return backgroundImageName;
    }

    @Override
    void syncModelView() {
        Player player = game.getPlayer();
        nameValueLabel.setText(player.getName());
        creditsValueLabel.setText("$" + player.getCredits().toString());
        pilotValueLabel.setText(player.getSkill(Skill.PILOT).toString());
        fighterValueLabel.setText(player.getSkill(Skill.FIGHTER).toString());
        merchantValueLabel.setText(player.getSkill(Skill.MERCHANT).toString());
        engineerValueLabel.setText(player.getSkill(Skill.ENGINEER).toString());
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, topPanel, midPanel, midTopPanel,
                midMidPanel, midBottomPanel, bottomPanel);
        UIStyle.addButtonStyle(okayButton, backButton);
        UIStyle.addPanelTranslucencyStyle(midPanel);
        UIStyle.addDisplayTextColorStyle(engineerLabel, engineerValueLabel, merchantLabel,
                merchantValueLabel, fighterLabel, fighterValueLabel, pilotLabel, pilotValueLabel,
                skillsLabel, creditsLabel, creditsValueLabel, nameLabel, nameValueLabel,
                settingsLabel);
    }
}
