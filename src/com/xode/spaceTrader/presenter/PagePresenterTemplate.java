package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Player;
import com.xode.spaceTrader.model.Skill;
import com.xode.spaceTrader.util.UIStyle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 * This class provides a template for defining a page controller classes. All page controller
 * classes except Main must extend the PagePresenter abstract class and implement necessary
 * methods. This class should not be created by yourself, rather, it should come raw as you create a
 * GUI form in IntelliJ. You can simply drag this class from package view to package controller, and
 * then add the keyword extends to extend the super abstract class PagePresenter. This class is
 * meant to provide a consistent design convention and simplify the logic toggling between JPanel
 * pages within the same JFrame. You can take a look at the PagePresenter class to understand how
 * inheritance and polymorphism work.
 */
public class PagePresenterTemplate extends PagePresenter {
    /**
     * Define this page's root Panel, usually automatically defined after crafting with IntelliJ's
     * GUI form.
     */
    private JPanel panelMain;

    /**
     * Define other components lying inside panelMain.
     */
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton buttonLinkToPageA;
    private JButton buttonLinkToPageB;
    private JButton buttonToChangeModel;
    private JLabel labelA;
    private JLabel labelB;
    private JLabel creditValueLabel;
    private JLabel nameValueLabel;
    // ... and more if there are

    /**
     * Define the page controllers that control the page panels your current page will link to.
     */
    private PagePresenter pagePresenterA;
    private PagePresenter pagePresenterB;
    // ... and more if there are

    /**
     * Define the model classes whose data will be displayed on this page panel.
     */
    private Player player;
    // and more if there are


    /**
     * Define the background image this page panel uses
     * set to null or empty "" if no background image desired
     */
    private String backgroundImageURL
            = "src/com/xcode/spaceTrader/resource/image/{IMAGE_NAME.extension}";

    /**
     * The no-arg constructor; must be no arg because IntelliJ GUI designer relies on this
     */
    PagePresenterTemplate() {
        // clearFields decides if the new page panel's data will be cleared before displaying;
        // **it will only work if you override the clearFields() method, see at bottom**
        buttonLinkToPageA.addActionListener(e -> goToPage(pagePresenterA));
        buttonLinkToPageB.addActionListener(e -> goToPage(pagePresenterB));

        // with buttons that change model, remember to call syncModelView()
        buttonToChangeModel.addActionListener(e -> {
            player.setSkill(Skill.PILOT, player.getSkill(Skill.PILOT) + 1);
            syncModelView();
        });
    }

    /**
     * The setters for page controllers that control the page panels your current page will link to
     * The Main controller should take care of linking between page controllers.
     * @param pagePresenterA the next page panel's controller class
     */
    void setPagePresenterA(PagePresenter pagePresenterA) {
        this.pagePresenterA = pagePresenterA;
    }
    void setPagePresenterB(PagePresenter pagePresenterB) {
        this.pagePresenterB = pagePresenterB;
    }

    /**
     * Implement the super abstract class's (PagePresenter) getPanelMain() method,
     * return this page's panelMain
     */
    @Override
    JPanel getPanelMain() {
        return panelMain;
    }

    /**
     * Implement the super abstract class's (PagePresenter) getBackgroundImageName method,
     * return this page's backgroundImageURL
     */
    @Override
    String getBackgroundImageName() {
        return backgroundImageURL;
    }

    /**
     * Implement the super abstract class's (PagePresenter) syncModelView method. The controller
     * must diligently synchronize the data stored in model and presented on view. This method
     * iterates all field on view and reflect updates from model to view. The following
     * implementation is just an example. Do not take it literally as each page panel controller
     * should implement its syncModelView() based on what is presented on the view.
     */
    @Override
    void syncModelView() {
        nameValueLabel.setText(player.getName());
        if (player.getCredits() == null) {
            creditValueLabel.setText("-");
        } else {
            creditValueLabel.setText(player.getCredits().toString());
        }
    }

    /**
     * OPTIONAL: implement the super abstract class's (PagePresenter) renderUIStyle method,
     * customizing this page's looking including transparency and other styles. It is not mandatory
     * because the super class provides a concrete empty method body
     */
    @Override
    void renderUIStyle() {
        // do rendering logic here
        // for example, if we want to create a transparent layout
        JComponent[] panels = {topPanel, bottomPanel};
        UIStyle.addTransparencyStyle(panels);

        // another example, if we want to apply additional styling to buttons
        JButton[] buttons = {buttonLinkToPageA, buttonLinkToPageB};
        UIStyle.addButtonStyle(buttons);

        // ... and many more styles if desired, but we will have to implement others in UIStyle.java
    }
}
