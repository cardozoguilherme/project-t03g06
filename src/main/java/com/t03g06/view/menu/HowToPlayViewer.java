package com.t03g06.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.menu.HowToPlayModel;
import com.t03g06.view.Viewer;

public class HowToPlayViewer implements Viewer {
    private final HowToPlayModel model;
    private final TextGraphics tg;

    public HowToPlayViewer(HowToPlayModel model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    @Override
    public void draw() {
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        tg.fill(' ');

        tg.putString(35, 2, "HOW TO PLAY");

        int y = 5;
        for (String instruction : model.getInstructions()) {
            tg.putString(15, y, instruction);
            y += 2;

        }

        tg.setForegroundColor(TextColor.ANSI.YELLOW);
        tg.putString(20, y + 2, "Press ESC to return to the menu.");

    }
}
