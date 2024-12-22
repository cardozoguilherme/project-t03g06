package com.t03g06.view.entities;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Bird;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BirdViewer {
    private final Bird bird;
    private final TextGraphics tg;

    public BirdViewer(Bird bird, TextGraphics tg) {
        this.bird = bird;
        this.tg = tg;
    }

    public void draw() {
        // desenha o bird
        tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        tg.putString(GameConstants.WIDTH / 4, bird.getY(), "O");
    }
}

