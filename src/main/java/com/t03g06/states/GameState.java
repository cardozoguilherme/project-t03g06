package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.controller.GameController;
import com.t03g06.model.GameModel;
import com.t03g06.view.GameViewer;
import com.t03g06.view.Viewer;

public class GameState extends State<GameModel> {
    public GameState(GameModel model, TextGraphics tg) {
        super(model, tg);
    }

    @Override
    public Viewer getViewer() {
        return new GameViewer(getModel(), getTextGraphics());
    }

    @Override
    public Controller getController() {
        return new GameController(getModel());
    }
}
