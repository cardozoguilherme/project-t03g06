package com.t03g06.view.elements;

import com.t03g06.model.GameConstants;
import com.t03g06.model.elements.Pipe;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class PipeViewer {
    private final List<Pipe> pipes;
    private final TextGraphics tg;

    public PipeViewer(List<Pipe> pipes, TextGraphics tg) {
        this.pipes = pipes;
        this.tg = tg;
    }

    public void draw() {
        // desenha os pipes
        tg.setBackgroundColor(TextColor.ANSI.GREEN);
        for (Pipe pipe : pipes) {
            for (int y = 0; y < GameConstants.HEIGHT; y++) {
                if (y < pipe.getGapStart() || y >= pipe.getGapStart() + GameConstants.PIPE_GAP) {
                    for (int w = 0; w < GameConstants.PIPE_WIDTH; w++) {
                        tg.putString(pipe.getX() + w, y, " ");
                    }
                }
            }
        }
        // volta à cor padrão do fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
    }
}
