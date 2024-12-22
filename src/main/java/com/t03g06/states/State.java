package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.Controller;
import com.t03g06.view.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final TextGraphics tg;

    public State(T model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    public T getModel() {
        return model;
    }

    public TextGraphics getTextGraphics() {
        return tg;
    }

    public abstract Viewer getViewer();

    public abstract Controller getController();

    public void step(Game game, Screen screen) throws IOException {
        getController().processInput(game);
        getViewer().draw();
        screen.refresh();
    }
}
