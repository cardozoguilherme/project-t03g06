package com.t03g06.view.elements;

import com.t03g06.model.elements.SpeedModifier;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class SpeedModifierViewer {
    private final List<SpeedModifier> speedModifiers;
    private final TextGraphics tg;

    public SpeedModifierViewer(List<SpeedModifier> speedModifiers, TextGraphics tg) {
        this.speedModifiers = speedModifiers;
        this.tg = tg;
    }

    public void draw() {
        tg.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        for (SpeedModifier speedModifier : speedModifiers) {
            // desenha um bloco 2x3
            tg.putString(speedModifier.getX(), speedModifier.getY(), ">>");
            tg.putString(speedModifier.getX(), speedModifier.getY() + 1, ">>");
            tg.putString(speedModifier.getX(), speedModifier.getY() + 2, ">>");
        }
    }
}
