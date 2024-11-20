package com.t03g06.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.Bird;
import com.t03g06.model.GameState;
import com.t03g06.model.Pipe;

public class GameView {
    private static final char BIRD_CHAR = 'O';  // representa o bird
    private static final char SPACE_CHAR = ' '; // representa o fundo
    private static final int WIDTH = 80;       // largura da tela
    private static final int HEIGHT = 40;      // altura da tela

    public void draw(TextGraphics tg, GameState gameState) {
        tg.setBackgroundColor(TextColor.ANSI.BLUE); // renderiza o fundo da tela na cor azul
        tg.setForegroundColor(TextColor.ANSI.WHITE); // renderiza texto na cor branca
        tg.fill(SPACE_CHAR); // preenche o fundo da tela

        // renderiza o bird
        drawBird(tg, gameState.getBird());

        // renderiza os canos
        drawPipes(tg, gameState);

        // renderiza a pontuação
        drawScore(tg, gameState.getScore());

        // renderiza a mensagem de game over
        if (gameState.isGameOver()) {
            drawGameOverMessage(tg);
        }
    }

    private void drawBird(TextGraphics tg, Bird bird) {
        tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT); // cor do bird
        tg.putString(WIDTH / 4, bird.getY(), String.valueOf(BIRD_CHAR)); // bird na tela (1/4 na horizontal e na altura y)
    }

    private void drawPipes(TextGraphics tg, GameState gameState) {
        for (Pipe pipe : gameState.getPipes()) {
            int x = pipe.getX();
            int gapStart = pipe.getGapStart();
            int gapSize = pipe.getGapSize();

            // passa por todas as linhas da tela
            for (int y = 0; y < GameState.getHeight(); y++) {
                if (y < gapStart || y >= gapStart + gapSize) { // partes do cano fora do gap
                    for (int w = 0; w < GameState.getPipeWidth(); w++) { // largura do cano
                        tg.setBackgroundColor(TextColor.ANSI.GREEN); // Fundo verde para os canos
                        tg.putString(x + w, y, " "); // Preenche o espaço com o fundo dos canos
                    }
                }
            }
        }

        // restaura o fundo padrão (azul)
        tg.setBackgroundColor(TextColor.ANSI.BLUE); // cor de fundo azul
    }

    private void drawScore(TextGraphics tg, int score) {
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(1, 1, "SCORE: " + score);
    }

    private void drawGameOverMessage(TextGraphics tg) {
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(WIDTH / 2 - 5, HEIGHT / 2, "GAME OVER!");
        tg.putString(WIDTH / 2 - 8, HEIGHT / 2 + 1, "PRESS R TO RESTART");
    }
}