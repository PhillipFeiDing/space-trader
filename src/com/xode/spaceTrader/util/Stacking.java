package com.xode.spaceTrader.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Stacking {
    public static final int BAR_HEIGHT = 22; // default barHeight is 22

    public static Container addBackground(JPanel panel, String imgName,
                                          int frameWidth, int frameHeight) {
        JLayeredPane parentPane = new JLayeredPane();
        parentPane.setLayout(null);

        parentPane.add(panel, 0);
        panel.setBounds(0, 0, frameWidth, frameHeight - BAR_HEIGHT);

        if (imgName == null || imgName.isEmpty()) {
            return parentPane;
        }

        try {
            Image img = ImageIO.read(Stacking.class.getResourceAsStream(
                    "/com/xode/spaceTrader/resource/image/" + imgName));
            JLabel imgLabel = new JLabel();
            imgLabel.setIcon(new ImageIcon(img));
            parentPane.add(imgLabel, 1);
            imgLabel.setBounds(0, -BAR_HEIGHT / 2, frameWidth, frameHeight);
        } catch (IOException e) {
            System.out.println("Image not found with this URL, image not added: " + imgName);
        }

        return parentPane;
    }

    public static void setIcon(JComponent component, String imgName) {
        if (imgName == null || imgName.isEmpty()) {
            return;
        }
        try {
            Image img = ImageIO.read(Stacking.class.getResourceAsStream(
                    "/com/xode/spaceTrader/resource/image/" + imgName));
            if (component instanceof JButton) {
                ((JButton) component).setIcon(new ImageIcon(img));
            } else if (component instanceof JLabel) {
                ((JLabel) component).setIcon(new ImageIcon(img));
            }
        } catch (IOException e) {
            System.out.println("Image not found with this URL, image not added: " + imgName);
        }
    }
}
