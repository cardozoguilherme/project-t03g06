package com.t03g06.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.model.GameModel;
import com.t03g06.states.GameOverState;
import com.t03g06.states.GameState;

import java.io.IOException;

public class GameController implements Controller {
    private final GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    @Override
    public void step(Game game) {
        if (model.isGameOver()) {
            // transição para o estado de Game Over
            game.setState(new GameOverState(model, game.getTextGraphics()));
            return;
        }

        try {
            // captura a entrada do teclado
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.Character) {
                    char key = keyStroke.getCharacter();
                    if (key == ' ') {
                        model.jumpBird(); // faz o pássaro pular
                    } else if (key == 'r' || key == 'R') {
                        // reinicia o jogo
                        GameModel newGameModel = new GameModel();
                        game.setState(new GameState(newGameModel, game.getTextGraphics()));
                        return; // sai do método após reiniciar
                    }
                } else if (keyStroke.getKeyType() == KeyType.Escape) {
                    System.exit(0); // encerra o jogo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // atualiza o estado do jogo
        model.updateGame();
    }
}
