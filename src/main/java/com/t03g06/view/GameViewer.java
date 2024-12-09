package com.t03g06.view;

import com.t03g06.model.GameModel;
import com.t03g06.model.Pipe;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameViewer implements Viewer<GameModel> {
    private final GameModel model;
    private final TextGraphics tg;

    public GameViewer(GameModel model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    @Override
    public void draw() {
        // desenha o fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.fill(' ');

        // desenha o bird
        tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        tg.putString(GameModel.WIDTH / 4, model.getBird().getY(), "O");

        // desenha os pipes
        tg.setBackgroundColor(TextColor.ANSI.GREEN);
        for (Pipe pipe : model.getPipes()) {
            for (int y = 0; y < GameModel.HEIGHT; y++) {
                if (y < pipe.getGapStart() || y >= pipe.getGapStart() + GameModel.PIPE_GAP) {
                    for (int w = 0; w < GameModel.PIPE_WIDTH; w++) {
                        tg.putString(pipe.getX() + w, y, " ");
                    }
                }
            }
        }

        // volta à cor padrão do fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);

        // desenha o score
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(2, 1, "SCORE: " + model.getScore());
    }
}
