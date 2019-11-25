package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.util.Stacking;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;

abstract class PagePresenter {
    private JFrame rootFrame;

    void setRootFrame(JFrame rootFrame) {
        this.rootFrame = rootFrame;
    }
    JFrame getRootFrame() {
        return this.rootFrame;
    }

    void goToPage(PagePresenter nextController) {
        nextController.redirect(this);
        nextController.reset();
        nextController.syncModelView();
        rootFrame.setContentPane(nextController.getMainContainer());
        rootFrame.setVisible(true);
    }

    Container getMainContainer() {
        renderUIStyle();
        return Stacking.addBackground(getPanelMain(), getBackgroundImageName(),
                rootFrame.getWidth(), rootFrame.getHeight());
    }

    abstract void syncModelView();
    abstract JPanel getPanelMain();
    abstract String getBackgroundImageName();
    void renderUIStyle() {
    }

    void reset() {
    }

    void redirect(PagePresenter previousPage) {
    }
}
