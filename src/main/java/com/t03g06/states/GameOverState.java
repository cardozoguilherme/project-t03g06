package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.controller.GameOverController;
import com.t03g06.model.GameModel;
import com.t03g06.view.GameOverViewer;
import com.t03g06.view.Viewer;

public class GameOverState extends State<GameModel> {
    public GameOverState(GameModel model, TextGraphics tg) {
        super(model, tg);
    }

    @Override
    public Viewer getViewer() {
        return new GameOverViewer(getTextGraphics());
    }

    @Override
    public Controller getController() {
        return new GameOverController();
    }
}
