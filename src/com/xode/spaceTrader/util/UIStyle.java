package com.xode.spaceTrader.util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIStyle {
    public static void addTransparencyStyle(JComponent... components) {
        for (JComponent component: components) {
            component.setOpaque(false);
        }
    }

    private static final Color BUTTON_ACTIVE_TEXT_COLOR = new Color(255, 255, 255);
    private static final Color BUTTON_ACTIVE_FILL_COLOR =  new Color(255, 255, 255, 50);
    private static final Color BUTTON_ACTIVE_BORDER_COLOR = new Color(29, 176, 255, 200);
    private static final Color BUTTON_HOVER_TEXT_COLOR = new Color(201, 255, 0);
    private static final Color BUTTON_HOVER_FILL_COLOR = new Color(255, 255, 255, 150);
    private static final Color BUTTON_HOVER_BORDER_COLOR = new Color(29, 176, 255, 100);
    private static final Cursor BUTTON_HOVER_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    private static final int BUTTON_RADIUS = 50;
    private static final int BUTTON_BORDER_WIDTH = 2;
    private static final RoundedBorderFill BUTTON_ACTIVE_STYLE = new RoundedBorderFillBuilder()
            .setRadius(BUTTON_RADIUS)
            .setBorderWidth(BUTTON_BORDER_WIDTH)
            .setBorderColor(BUTTON_ACTIVE_BORDER_COLOR)
            .setFillColor(BUTTON_ACTIVE_FILL_COLOR).build();
    private static final RoundedBorderFill BUTTON_HOVER_STYLE = new RoundedBorderFillBuilder()
            .setRadius(BUTTON_RADIUS)
            .setBorderWidth(BUTTON_BORDER_WIDTH)
            .setBorderColor(BUTTON_HOVER_BORDER_COLOR)
            .setFillColor(BUTTON_HOVER_FILL_COLOR).build();
    public static void addButtonStyle(JButton... buttons) {
        for (JButton button: buttons) {
            notHover(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (button.isEnabled()) {
                        hover(button);
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (button.isEnabled()) {
                        notHover(button);
                    }
                }
            });
            button.addPropertyChangeListener("enabled", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    notHover(button);
                }
            });
        }
    }
    private static void notHover(JButton button) {
        button.setForeground(BUTTON_ACTIVE_TEXT_COLOR);
        button.setFont(button.getFont().deriveFont(Font.PLAIN));
        button.setBorder(BUTTON_ACTIVE_STYLE);
        button.setCursor(Cursor.getDefaultCursor());
    }

    private static void hover(JButton button) {
        button.setForeground(BUTTON_HOVER_TEXT_COLOR);
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        button.setBorder(BUTTON_HOVER_STYLE);
        button.setCursor(BUTTON_HOVER_CURSOR);
    }

    private static final Color TEXT_FIELD_BACKGROUND_COLOR = new Color(255, 255, 255, 64);
    private static final Color TEXT_FIELD_TEXT_COLOR = new Color(255, 255, 255);
    public static void addTextDisplayTranslucentStyle(JComponent... components) {
        for (JComponent textDisplay: components) {
            textDisplay.setOpaque(false);
            // color of the text field
            textDisplay.setBackground(TEXT_FIELD_BACKGROUND_COLOR);
            // color of input text
            textDisplay.setForeground(TEXT_FIELD_TEXT_COLOR);
        }
    }

    private static final Color PANEL_TRANSLUCENT_COLOR = new Color(255, 255, 255, 50);
    private static final int PANEL_BORDER_RADIUS = 25;
    private static final RoundedBorderFill PANEL_STYLE = new RoundedBorderFillBuilder()
            .setRadius(PANEL_BORDER_RADIUS)
            .setFillColor(PANEL_TRANSLUCENT_COLOR)
            .setBorderColor(PANEL_TRANSLUCENT_COLOR).setBorderWidth(0).build();
    public static void addPanelTranslucencyStyle(JPanel... panels) {
        for (JPanel panel: panels) {
            panel.setBorder(PANEL_STYLE);
        }
    }

    private static final int PROGESS_BAR_RADIUS = 30;
    private static final int PROGESS_BAR_BORDER_WIDTH = 2;
    private static final Color PROGRESS_BAR_BORDER_COLOR = new Color(255, 255, 255, 150);
    private static final Color PROGRESS_BAR_DEFAULT_FILL_COLOR = PROGRESS_BAR_BORDER_COLOR;
    private static final Color PROGRESS_BAR_TEXT_COLOR = new Color(255, 255, 255);
    public static final Color FUEL_COLOR = new Color(0, 255, 0, 150);
    public static final Color HULL_COLOR = new Color(0, 0, 255, 150);
    private static final RoundedBorderFill PROGRESS_BAR_STYLE = new RoundedBorderFillBuilder()
            .setRadius(PROGESS_BAR_RADIUS)
            .setBorderWidth(PROGESS_BAR_BORDER_WIDTH)
            .setBorderColor(PROGRESS_BAR_BORDER_COLOR)
            .setFillColor(PANEL_TRANSLUCENT_COLOR).build();
    public static void addProgressBarStyle(JPanel... panels) {
        for (JPanel panel: panels) {
            panel.setForeground(PROGRESS_BAR_TEXT_COLOR);
            panel.setBorder(PROGRESS_BAR_STYLE);
        }
    }
    public static void repaintWithProgressBarStyle(JPanel panel, double fill, Color fillColor) {
        RoundedBorderFill tempBorderFill = new RoundedBorderFillBuilder()
                .setRadius(PROGESS_BAR_RADIUS)
                .setBorderWidth(PROGESS_BAR_BORDER_WIDTH)
                .setBorderColor(PROGRESS_BAR_BORDER_COLOR)
                .setFillColor(fillColor)
                .setFill(fill).build();
        panel.setBorder(tempBorderFill);
    }

    public static void addRegionButtonStyle(JButton... buttons) {
        for (JButton button: buttons) {
            button.setBorderPainted(false);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button.setCursor(BUTTON_HOVER_CURSOR);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setCursor(Cursor.getDefaultCursor());
                }
            });
        }
    }

    public static void addHoverHandCursorStyle(JComponent... components) {
        for (Component component: components) {
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    component.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
    }

    private static final Color GOODS_TRANSLUCENT_COLOR = new Color(255, 255, 255, 75);
    public static final Color GOODS_FOCUS_COLOR = new Color(201, 255, 0, 75);
    private static final int GOODS_BORDER_RADIUS = 10;
    private static final RoundedBorderFill GOODS_STYLE = new RoundedBorderFillBuilder()
            .setFillColor(GOODS_TRANSLUCENT_COLOR)
            .setRadius(GOODS_BORDER_RADIUS)
            .setBorderColor(GOODS_TRANSLUCENT_COLOR).setBorderWidth(0).build();
    private static final RoundedBorderFill GOODS_FOCUS_STYLE = new RoundedBorderFillBuilder()
            .setFillColor(GOODS_FOCUS_COLOR)
            .setRadius(GOODS_BORDER_RADIUS)
            .setBorderColor(GOODS_TRANSLUCENT_COLOR).setBorderWidth(0).build();
    public static void addGoodsPanelStyle(int capacity, JPanel... panels) {
        int count = 0;
        for (JPanel panel: panels) {
            if (count < capacity) {
                panel.setBorder(GOODS_STYLE);
            } else {
                panel.setBorder(null);
            }
            count++;
        }
    }
    public static void addGoodsPanelFocusStyle(JPanel panel) {
        panel.setBorder(GOODS_FOCUS_STYLE);
    }

    private static final Color DISPLAY_TEXT_COLOR = new Color(255, 255, 255);
    private static final Color ERROR_TEXT_COLOR = new Color(255, 0, 0);
    public static void addDisplayTextColorStyle(JComponent... components) {
        addTextColorStyle(DISPLAY_TEXT_COLOR, components);
    }
    public static void addErrorTextColorStyle(JComponent... components) {
        addTextColorStyle(ERROR_TEXT_COLOR, components);
    }
    private static void addTextColorStyle(Color textColor, JComponent... components) {
        for (Component component: components) {
            component.setForeground(textColor);
        }
    }


    private static class RoundedBorderFill implements Border {

        private final int radius;
        private final int strokeWidth;
        private final Color borderColor;
        private final Color fillColor;
        private final double fill;

        RoundedBorderFill(int radius, int strokeWidth,
                          Color borderColor, Color fillColor, double fill) {
            this.radius = radius;
            this.strokeWidth = strokeWidth;
            this.borderColor = borderColor;
            this.fillColor = fillColor;
            this.fill = fill;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Stroke tempStroke = g2d.getStroke();
            Color tempColor = g2d.getColor();
            if (fillColor != null) {
                g2d.setColor(fillColor);
                Shape temp = g2d.getClip();
                if (fill < 1) {
                    g2d.clipRect(0, 0, (int) (temp.getBounds().width * fill),
                            temp.getBounds().height);
                }
                g2d.fillRoundRect(x + strokeWidth / 2, y + strokeWidth - 1,
                        width - strokeWidth, height - 2 * strokeWidth + 2,
                        radius, radius);
                g2d.setClip(temp);
            }
            if (borderColor != null && strokeWidth != 0) {
                g2d.setStroke(new BasicStroke(strokeWidth));
                g2d.setColor(borderColor);
                g2d.drawRoundRect(x + strokeWidth / 2, y + strokeWidth / 2,
                        width - strokeWidth, height - strokeWidth,
                        radius, radius);
            }
            g2d.setColor(tempColor);
            g2d.setStroke(tempStroke);
        }
    }

    private static class RoundedBorderFillBuilder {
        private int radius = 0;
        private int strokeWidth = 0;
        private Color borderColor = null;
        private Color fillColor = null;
        private double fill = 1.0;

        RoundedBorderFillBuilder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        RoundedBorderFillBuilder setBorderWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        RoundedBorderFillBuilder setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        RoundedBorderFillBuilder setFillColor(Color fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        RoundedBorderFillBuilder setFill(double fill) {
            this.fill = fill;
            return this;
        }

        RoundedBorderFill build() {
            return new RoundedBorderFill(radius, strokeWidth, borderColor, fillColor, fill);
        }
    }
}
