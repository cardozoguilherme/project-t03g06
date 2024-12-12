package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.controller.PlayingController;
import com.t03g06.model.GameModel;
import com.t03g06.view.GameViewer;
import com.t03g06.view.Viewer;

public class PlayingState extends State<GameModel> {
    public PlayingState(GameModel model, TextGraphics tg) {
        super(model, tg);
    }

    @Override
    protected Viewer getViewer() {
        return new GameViewer(getModel(), getTextGraphics());
    }

    @Override
    protected Controller getController() {
        return new PlayingController(getModel());
    }
}
