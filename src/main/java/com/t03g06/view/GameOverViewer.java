package com.t03g06.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

public class GameOverViewer implements Viewer {
    private final TextGraphics tg;

    public GameOverViewer(TextGraphics tg) {
        this.tg = tg;
    }

    @Override
    public void draw() {
        tg.setBackgroundColor(TextColor.ANSI.RED);
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(35, 10, "GAME OVER");
        tg.putString(30, 12, "Press 'R' to Restart");
        tg.putString(30, 14, "Press 'Esc' to Exit");
    }
}
