package com.t03g06.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.model.GameModel;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.GameState;
import com.t03g06.states.MenuState;

import java.io.IOException;

public class GameOverController implements Controller {

    @Override
    public void step(Game game) {
        try {
            // captura a entrada do teclado
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.Character) {
                    char key = keyStroke.getCharacter();
                    if (key == 'r' || key == 'R') {
                        // reinicia o jogo
                        GameModel newGameModel = new GameModel();
                        game.setState(new GameState(newGameModel, game.getTextGraphics()));
                    }
                } else if (keyStroke.getKeyType() == KeyType.Escape) {
                    game.setState(new MenuState(new MenuModel(), game.getTextGraphics()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
