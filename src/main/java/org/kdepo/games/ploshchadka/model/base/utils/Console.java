package org.kdepo.games.ploshchadka.model.base.utils;

import org.kdepo.games.ploshchadka.model.base.fonts.Font;
import org.kdepo.games.ploshchadka.model.base.fonts.FontUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Console {

    private static final List<String> MESSAGES = new ArrayList<>();

    private static final Font FONT = FontUtils.load("fonts/font_n13x15o.xml", "fonts/font_n13x15o.png");

    public static void addMessage(String message) {
        MESSAGES.add(message);
    }

    public static void clear() {
        MESSAGES.clear();
    }

    public static void draw(Graphics g) {
        if (FONT == null) {
            return;
        }

        int x = 10;
        int y = 0;
        for (String message : MESSAGES) {
            FONT.render((Graphics2D) g, message, x, y);
            y = y + 15;
        }
    }
}
