package org.kdepo.games.ploshchadka.model.base.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Console {

    private static final List<String> MESSAGES = new ArrayList<>();

    public static void addMessage(String message) {
        MESSAGES.add(message);
    }

    public static void clear() {
        MESSAGES.clear();
    }

    public static void draw(Graphics g) {
        int x = 10;
        int y = 15;

        g.setColor(Color.YELLOW);

        for (String message : MESSAGES) {
            g.drawString(message, x, y);
            y = y + 12;
        }
    }
}
