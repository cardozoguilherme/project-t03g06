package com.t03g06.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.model.GameState;

public class InputController {
    private final GameState gameState;

    public InputController(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean processInput(KeyStroke keyStroke) {
        if (keyStroke.getKeyType() == KeyType.Character) {
            char key = keyStroke.getCharacter();
            if (key == ' ') {
                gameState.jumpBird(); // faz o bird pular
            } else if (key == 'r') {
                gameState.resetGame(); // reinicia o jogo
            }
        } else return keyStroke.getKeyType() != KeyType.Escape; // sai do jogo
        return true; // continua o jogo
    }
}
