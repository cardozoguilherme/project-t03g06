package com.t03g06.view;

import com.googlecode.lanterna.TextColor;
import com.t03g06.model.GameModel;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.view.entities.BirdViewer;
import com.t03g06.view.entities.CoinViewer;
import com.t03g06.view.entities.PipeViewer;
import com.t03g06.view.entities.SpeedModifierViewer;

public class GameViewer implements Viewer {
    private final GameModel model;
    private final TextGraphics tg;
    private final BirdViewer birdViewer;
    private final PipeViewer pipeViewer;
    private final CoinViewer coinViewer;
    private final SpeedModifierViewer speedModifierViewer;

    public GameViewer(GameModel model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
        this.birdViewer = new BirdViewer(model.getBird(), tg);
        this.coinViewer = new CoinViewer(model.getCoinManager().getCoins(), tg);
        this.speedModifierViewer = new SpeedModifierViewer(model.getSpeedModifierManager().getSpeedModifiers(), tg);
        this.pipeViewer = new PipeViewer(model.getPipeManager().getPipes(), tg);
    }

    public GameViewer(GameModel model, TextGraphics tg, BirdViewer birdViewer, CoinViewer coinViewer,
                      PipeViewer pipeViewer, SpeedModifierViewer speedModifierViewer) {
        this.model = model;
        this.tg = tg;
        this.birdViewer = birdViewer;
        this.coinViewer = coinViewer;
        this.pipeViewer = pipeViewer;
        this.speedModifierViewer = speedModifierViewer;
    }

    @Override
    public void draw() {
        // desenha o fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.fill(' ');

        // desenha os elementos
        birdViewer.draw();
        coinViewer.draw();
        speedModifierViewer.draw();
        pipeViewer.draw();

        // desenha o score
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(2, 1, "SCORE: " + model.getScore());
    }
}
