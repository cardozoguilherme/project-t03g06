package com.t03g06.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    public static final int WIDTH = 80; // largura da janela
    public static final int HEIGHT = 40; // altura da janela
    public static final int PIPE_WIDTH = 5; // largura do pipe
    public static final int PIPE_GAP = 12; // espaço onde o bird passa
    public static final int PIPE_DISTANCE = 10; // distância dos canos na horizontal
    public static final int MARGIN = 4; // controla a distância minima do gap para o chão/teto
    public static final int GRAVITY = 1; // gravidade, move bird para baixo
    public static final int JUMP_HEIGHT = 3; // altura do pulo do bird
    public static final int PIPES_COUNT = 10; // quantidade de pipes que iniciam no jogo

    private final Bird bird;
    private final List<Pipe> pipes;
    private final Random random;
    private int score;
    private boolean gameOver = false;
    private boolean started = false;

    public GameModel() {
        bird = new Bird(HEIGHT / 2); // altura do bird é metade da altura da janela
        pipes = new ArrayList<>();
        random = new Random();
        resetGame();
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
        for (int i = 0; i < PIPES_COUNT; i++) {
            addPipe(WIDTH / 2 + i * (PIPE_WIDTH + PIPE_DISTANCE));
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
        if (!pipes.isEmpty() && pipes.getFirst().isOutOfScreen()) {
            pipes.removeFirst();
            addNewPipe();
        }

        bird.applyGravity(GRAVITY);
        checkCollision();
    }

    private void checkCollision() {
        int birdX = WIDTH / 4;
        int birdY = bird.getY();

        // checa colisão do bird com pipe
        for (Pipe pipe : pipes) {
            if (birdX >= pipe.getX() && birdX <= pipe.getX() + PIPE_WIDTH) {
                if (birdY < pipe.getGapStart() || birdY >= pipe.getGapStart() + pipe.getGapSize()) {
                    gameOver = true;
                }
            }
        }

        // checa colisão do bird com o chão ou teto
        if (birdY < 0 || birdY >= HEIGHT) {
            gameOver = true;
        }
    }

    public void jumpBird() {
        started = true;
        bird.jump(JUMP_HEIGHT);
    }
}