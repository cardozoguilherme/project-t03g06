package com.t03g06.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.model.GameState;
import com.t03g06.view.GameView;

import java.io.IOException;

public class GameController {
    private final GameState gameState;
    private final GameView gameView;
    private final InputController inputController;
    private final Screen screen;

    public GameController(GameState gameState, GameView gameView, Screen screen) {
        this.gameState = gameState;
        this.gameView = gameView;
        this.screen = screen;
        this.inputController = new InputController(gameState);
    }

    public void run() throws IOException {
        while (true) {
            KeyStroke keyStroke = screen.pollInput();

            // processa a entrada do usuário
            if (keyStroke != null) {
                if (!inputController.processInput(keyStroke)) {
                    break; // sai do jogo (usuário apertou Esc)
                }
            }

            // atualiza o estado do jogo (movimenta os canos, verifica colisões, aplica gravidade e verifica pontuação)
            gameState.updateGame();

            // renderiza o jogo
            TextGraphics tg = screen.newTextGraphics();
            gameView.draw(tg, gameState);
            screen.refresh();

            // controla o atraso das iterações do loop (velocidade do jogo)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // fecha o jogo
        screen.stopScreen();
    }
}

