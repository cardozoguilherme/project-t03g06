package com.t03g06.controller.menu;

import com.t03g06.Game;
import com.t03g06.controller.Controller;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.MenuState;

import java.io.IOException;

public class LeaderboardController implements Controller {

    public LeaderboardController() {
    }

    @Override
    public void processInput(Game game) {
        try {
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    game.setState(new MenuState(new MenuModel(), game.getTextGraphics()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
