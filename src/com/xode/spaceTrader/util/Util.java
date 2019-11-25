package com.xode.spaceTrader.util;

import com.xode.spaceTrader.model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static int findIdxInArray(Object obj, Object[] objs) {
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    public static void setGoodsDisplay(
            JPanel[] panels, JLabel[] goodsLabels, List<Goods> goods, JLabel[] priceLabels,
            List<Integer> prices) {
        for (int i = 0; i < panels.length; i++) {
            if (i < goods.size()) {
                panels[i].setEnabled(true);
                goodsLabels[i].setText(goods.get(i).toString());
                if (priceLabels != null) {
                    priceLabels[i].setText("$" + prices.get(i).toString());
                }
            } else {
                panels[i].setEnabled(false);
                goodsLabels[i].setText(" ");
                if (priceLabels != null) {
                    priceLabels[i].setText(" ");
                }
            }
        }
    }

    public static <T> List<Integer> randomSelect(List<T> objs, int count) {
        if (count > objs.size()) {
            throw new IllegalArgumentException("count > obj.size() in randomSelect();");
        }

        ArrayList<Integer> allIndices = new ArrayList<>();
        for (int i = 0; i < objs.size(); i++) {
            allIndices.add(i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(allIndices.remove(FixedRandom.nextInt(allIndices.size())));
        }
        return result;
    }
}
