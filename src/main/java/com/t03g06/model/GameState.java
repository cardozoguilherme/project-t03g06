package com.t03g06.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static final int PIPE_WIDTH = 5;
    private static final int PIPE_GAP = 12;
    private static final int PIPE_DISTANCE = 10;
    private static final int MARGIN = 4; // controla a distância minima do gap para o chão/teto

    private final Bird bird;
    private final List<Pipe> pipes;
    private final Random random;
    private int score;
    private boolean gameOver = false;
    private boolean started = false;

    public GameState() {
        bird = new Bird(HEIGHT / 2); // altura do bird é metade da altura
        pipes = new ArrayList<>();
        random = new Random();
        resetGame();
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getPipeWidth() {
        return PIPE_WIDTH;
    }

    public Bird getBird() {
        return bird;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void resetGame() {
        bird.reset();
        score = 0;
        gameOver = false;
        started = false;
        pipes.clear(); // esvazia a lista

        // inicializa os 10 primeiros canos
        for (int i = 0; i < 10; i++) {
            addPipe(WIDTH + i * (PIPE_WIDTH + PIPE_DISTANCE));
        }
    }

    private void addPipe(int x) {
        int gapStart = MARGIN + random.nextInt(HEIGHT - PIPE_GAP - 2 * MARGIN);
        pipes.add(new Pipe(x, gapStart, PIPE_GAP));
    }

    public void addNewPipe() {
        if (!pipes.isEmpty()) {
            int lastPipeX = pipes.getLast().getX();
            addPipe(lastPipeX + PIPE_WIDTH + PIPE_DISTANCE);
        }
    }

    public void updateGame() {
        // interrompe caso o jogo não tenha começado ou o jogador perdeu
        if (!started || gameOver) return;

        // move os canos para a esquerda
        for (Pipe pipe : pipes) {
            pipe.moveLeft();
        }

        // verifica se o bird passou o cano
        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && (WIDTH / 4) > (pipe.getX() + PIPE_WIDTH)) { // passou
                pipe.setScored(true);
                score++;
            }
        }

        // remove canos que estão fora da tela e adiciona novos
        if (!pipes.isEmpty() && pipes.getFirst().isOutOfScreen(WIDTH)) {
            pipes.removeFirst();
            addNewPipe();
        }

        bird.applyGravity();

        // checa colisão do bird com pipe
        for (Pipe pipe : pipes) {
            if (WIDTH / 4 >= pipe.getX() && WIDTH / 4 < pipe.getX() + PIPE_WIDTH) {
                if (bird.getY() < pipe.getGapStart() || bird.getY() >= pipe.getGapStart() + PIPE_GAP) {
                    gameOver = true;
                }
            }
        }

        // checa colisão do bird com o chão ou teto
        if (bird.getY() < 0 || bird.getY() >= HEIGHT) {
            gameOver = true;
        }
    }

    public void jumpBird() {
        started = true;
        bird.jump();
    }
}