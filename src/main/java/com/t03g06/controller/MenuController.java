package com.t03g06.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.model.GameModel;
import com.t03g06.model.MenuModel;
import com.t03g06.states.PlayingState;

import java.io.IOException;

public class MenuController implements Controller {
    private final MenuModel model;

    public MenuController(MenuModel model) {
        this.model = model;
    }

    @Override
    public void step(Game game) {
        try {
            // captura a entrada do teclado
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                    model.nextOption(); // move para a próxima opção
                } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                    model.previousOption(); // move para a opção anterior
                } else if (keyStroke.getKeyType() == KeyType.Enter) {
                    switch (model.getCurrentOption()) {
                        case 0 -> game.setState(new PlayingState(new GameModel(), game.getTextGraphics())); // inicia o jogo com GameModel
                        case 1 -> System.exit(0); // sai do jogo
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
