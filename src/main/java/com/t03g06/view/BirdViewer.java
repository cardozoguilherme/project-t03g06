package com.t03g06.view;

import com.t03g06.model.Bird;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameModel;

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
        tg.putString(GameModel.WIDTH / 4, bird.getY(), "O");
    }
}

