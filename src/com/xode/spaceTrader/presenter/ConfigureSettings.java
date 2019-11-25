package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.*;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigureSettings extends PagePresenter {
    private JPanel panelMain;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JPanel midTopPanel;
    private JPanel midBottomPanel;
    private JPanel midBottomBottomPanel;
    private JPanel midBottomBottomLeftPanel;
    private JPanel midBottomBottomRightPanel;
    private JTextField nameValueTextField;
    private JRadioButton easyRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton hardRadioButton;
    private JButton pilotMinusButton;
    private JButton pilotPlusButton;
    private JButton fighterMinusButton;
    private JButton merchantMinusButton;
    private JButton engineerMinusButton;
    private JButton fighterPlusButton;
    private JButton merchantPlusButton;
    private JButton engineerPlusButton;
    private JButton okayButton;
    private JButton backButton;
    private JLabel newGameLabel;
    private JLabel nameLabel;
    private JLabel difficultyLabel;
    private JLabel skillLabel;
    private JLabel pointsRemainingLabel;
    private JLabel pointsRemainingValueLabel;
    private JLabel pilotLabel;
    private JLabel fighterLabel;
    private JLabel merchantLabel;
    private JLabel engineerLabel;
    private JLabel pilotValueLabel;
    private JLabel fighterValueLabel;
    private JLabel merchantValueLabel;
    private JLabel engineerValueLabel;
    private JLabel initialCreditsValueLabel;
    private JLabel initialCreditsLabel;
    private JLabel errorMessageLabel;

    private final JButton[] plusButtons = {
        pilotPlusButton,
        fighterPlusButton,
        merchantPlusButton,
        engineerPlusButton
    };
    private final JButton[] minusButtons = {
        pilotMinusButton,
        fighterMinusButton,
        merchantMinusButton,
        engineerMinusButton
    };
    private final Object[][] skillButtonLookUp = {
            {Skill.PILOT, pilotMinusButton, pilotPlusButton},
            {Skill.FIGHTER, fighterMinusButton, fighterPlusButton},
            {Skill.ENGINEER, engineerMinusButton, engineerPlusButton},
            {Skill.MERCHANT, merchantMinusButton, merchantPlusButton}
    };
    private final Map<Skill, JButton> skillToMinusButton = Stream.of(skillButtonLookUp)
            .collect(Collectors.toMap(data -> (Skill) data[0], data -> (JButton) data[1]));
    private final Map<JButton, Skill> minusButtonToSkill = Stream.of(skillButtonLookUp)
            .collect(Collectors.toMap(data -> (JButton) data[1], data -> (Skill) data[0]));
    private final Map<JButton, Skill> plusButtonToSkill = Stream.of(skillButtonLookUp)
            .collect(Collectors.toMap(data -> (JButton) data[2], data -> (Skill) data[0]));
    private final Map<JRadioButton, Difficulty> radioButtonToDifficulty = Stream.of(new Object[][] {
            {easyRadioButton, Difficulty.EASY},
            {mediumRadioButton, Difficulty.MEDIUM},
            {hardRadioButton, Difficulty.HARD}
    }).collect(Collectors.toMap(data -> (JRadioButton) data[0], data -> (Difficulty) data[1]));

    private PagePresenter confirmSettings;
    private PagePresenter welcome;

    private Game game;
    private Integer pointsRemain;

    private String backgroundImageURL =
             "background.jpeg";

    ConfigureSettings() {
        for (JRadioButton difficultyButton: radioButtonToDifficulty.keySet()) {
            difficultyButton.addActionListener(e -> {
                game.setDifficulty(radioButtonToDifficulty.get(difficultyButton));
                assignCreditsPoints(game.getDifficulty().getInitialCredits(),
                        game.getDifficulty().getSkillPoints());
                syncModelView();
            });
        }

        for (JButton minusButton: minusButtonToSkill.keySet()) {
            minusButton.addActionListener(e -> {
                incRemainDecSkill(minusButtonToSkill.get(minusButton));
                syncModelView();
            });
        }
        for (JButton plusButton: plusButtonToSkill.keySet()) {
            plusButton.addActionListener(e -> {
                incSkillDecRemain(plusButtonToSkill.get(plusButton));
                syncModelView();
            });
        }

        nameValueTextField.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    game.getPlayer().setName(nameValueTextField.getText());
                    checkEnableConfirm();
                }
                public void removeUpdate(DocumentEvent e) {
                    game.getPlayer().setName(nameValueTextField.getText());
                    checkEnableConfirm();
                }
                public void insertUpdate(DocumentEvent e) {
                    game.getPlayer().setName(nameValueTextField.getText());
                    checkEnableConfirm();
                }
            }
        );

        okayButton.addActionListener(e -> {
            goToPage(confirmSettings);
        });
        okayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (!okayButton.isEnabled()) {
                    errorMessageLabel.setText(getErrorMessage());
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                errorMessageLabel.setText("");
            }
        });

        backButton.addActionListener(e -> {
            goToPage(welcome);
        });

        enableDisableButtons(plusButtons, false);
        enableDisableButtons(minusButtons, false);
        okayButton.setEnabled(false);
    }

    void setConfirmSettings(PagePresenter confirmSettings) {
        this.confirmSettings = confirmSettings;
    }
    void setWelcome(PagePresenter welcome) {
        this.welcome = welcome;
    }

    void setGame(Game game) {
        this.game = game;
    }

    private void clearAllSkillPoints() {
        Player player = game.getPlayer();
        player.setSkill(Skill.PILOT, 0);
        player.setSkill(Skill.ENGINEER, 0);
        player.setSkill(Skill.MERCHANT, 0);
        player.setSkill(Skill.FIGHTER, 0);
    }

    private void assignCreditsPoints(int credits, int points) {
        game.getPlayer().setCredits(credits);
        pointsRemain = points;
        clearAllSkillPoints();
        enableDisableButtons(plusButtons, true);
        enableDisableButtons(minusButtons, false);
        checkEnableConfirm();
    }

    private void incRemainDecSkill(Skill skill) {
        Player player = game.getPlayer();
        if (player.getSkill(skill) > 0) {
            player.setSkill(skill, player.getSkill(skill) - 1);
            pointsRemain++;
        }
        checkEnablePlusMinus(skill, true);
        checkEnableConfirm();
    }

    private void incSkillDecRemain(Skill skill) {
        Player player = game.getPlayer();
        if (pointsRemain > 0) {
            player.setSkill(skill, player.getSkill(skill) + 1);
            pointsRemain--;
        }
        checkEnablePlusMinus(skill, false);
        checkEnableConfirm();
    }

    private void checkEnablePlusMinus(Skill skill, boolean pointsRemainIncreased) {
        if (pointsRemainIncreased) {
            enableDisableButtons(plusButtons, true);
            if (game.getPlayer().getSkill(skill) == 0) {
                JButton[] buttons = {skillToMinusButton.get(skill)};
                enableDisableButtons(buttons, false);
            }
        } else {
            JButton[] buttons = {skillToMinusButton.get(skill)};
            enableDisableButtons(buttons, true);
            if (pointsRemain == 0) {
                enableDisableButtons(plusButtons, false);
            }
        }
    }

    private void checkEnableConfirm() {
        if (getErrorMessage().isEmpty()) {
            okayButton.setEnabled(true);
        } else {
            okayButton.setEnabled(false);
        }
    }

    private String getErrorMessage() {
        String name = nameValueTextField.getText();
        if (isWhiteSpace(name)) {
            return "Please name your character with at least one non-whitespace char.";
        } else if (pointsRemain == null) {
            return "Please select a difficulty first.";
        } else if (pointsRemain != 0) {
            return "Please use up all skill points.";
        }
        return "";
    }

    private boolean isWhiteSpace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void enableDisableButtons(JButton[] buttons, boolean enable) {
        for (JButton button: buttons) {
            button.setEnabled(enable);
        }
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
        Player player = game.getPlayer();
        initialCreditsValueLabel.setText(
                player.getCredits() == null ? "-" : player.getCredits().toString());
        pilotValueLabel.setText(player.getSkill(Skill.PILOT).toString());
        fighterValueLabel.setText(player.getSkill(Skill.FIGHTER).toString());
        merchantValueLabel.setText(player.getSkill(Skill.MERCHANT).toString());
        engineerValueLabel.setText(player.getSkill(Skill.ENGINEER).toString());
        pointsRemainingValueLabel.setText(pointsRemain == null ? "-" : pointsRemain.toString());
    }

    @Override
    void renderUIStyle() {
        UIStyle.addTransparencyStyle(panelMain, topPanel, midPanel, bottomPanel, midTopPanel,
                midBottomPanel, midBottomBottomPanel, midBottomBottomLeftPanel,
                midBottomBottomRightPanel);
        UIStyle.addTextDisplayTranslucentStyle(nameValueTextField);
        UIStyle.addButtonStyle(pilotMinusButton, pilotPlusButton, fighterMinusButton,
                merchantMinusButton, engineerMinusButton, fighterPlusButton, merchantPlusButton,
                engineerPlusButton, okayButton, backButton);
        UIStyle.addPanelTranslucencyStyle(midBottomPanel);
        UIStyle.addHoverHandCursorStyle(easyRadioButton, mediumRadioButton, hardRadioButton);
        UIStyle.addDisplayTextColorStyle(newGameLabel,  nameLabel, difficultyLabel, skillLabel,
                pointsRemainingLabel, pointsRemainingValueLabel, pilotLabel, fighterLabel,
                merchantLabel, engineerLabel, pilotValueLabel, fighterValueLabel,
                merchantValueLabel, engineerValueLabel, initialCreditsValueLabel,
                initialCreditsLabel, errorMessageLabel, easyRadioButton, mediumRadioButton,
                hardRadioButton);
        UIStyle.addErrorTextColorStyle(errorMessageLabel);
    }
}
