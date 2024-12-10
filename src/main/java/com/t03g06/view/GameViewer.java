package com.t03g06.view;

import com.googlecode.lanterna.TextColor;
import com.t03g06.model.GameModel;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameViewer implements Viewer<GameModel> {
    private final GameModel model;
    private final TextGraphics tg;
    private final BirdViewer birdViewer;
    private final PipeViewer pipeViewer;
    private final CoinViewer coinViewer;

    public GameViewer(GameModel model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
        this.birdViewer = new BirdViewer(model.getBird(), tg);
        this.pipeViewer = new PipeViewer(model.getPipes(), tg);
        this.coinViewer = new CoinViewer(model.getCoins(), tg);
    }

    @Override
    public void draw() {
        // desenha o fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.fill(' ');

        // desenha os elementos
        birdViewer.draw();
        pipeViewer.draw();
        coinViewer.draw();

        // desenha o score
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(2, 1, "SCORE: " + model.getScore());
    }
}
