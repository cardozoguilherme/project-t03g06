package com.t03g06.model;

import com.t03g06.model.elements.Bird;
import com.t03g06.model.elements.Coin;
import com.t03g06.model.elements.Pipe;
import com.t03g06.model.elements.SpeedModifier;

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
    public static final int COIN_WIDTH = 2; // largura da coin
    public static final int COIN_HEIGHT = 2; // altura da coin
    public static final int PIPE_SCORE = 1; // pontuação por passar o pipe
    public static final int COIN_SCORE = 5; // pontuação por coletar coin
    public static final int COIN_MARGIN = 10; // distância mínima da coin para o chão/teto
    public static final int SPEED_MODIFIER_WIDTH = 2;
    public static final int SPEED_MODIFIER_HEIGHT = 3;
    public static final int SPEED_MODIFIER_MARGIN = 10;

    private final Bird bird;
    private final List<Pipe> pipes;
    private final List<Coin> coins;
    private final List<SpeedModifier> speedModifiers;
    private final Random random;
    private int score;
    private boolean gameOver = false;
    private boolean started = false;
    private int pipeSpeed = 1;
    private int speedModifierSpeed = 1;
    private int coinSpeed = 1;
    private long speedModifierEndTime = 0; // tempo em que o modificador expira
    private static final int SPEED_MODIFIER_DURATION = 5000;

    public GameModel() {
        bird = new Bird(HEIGHT / 2); // altura do bird é metade da altura da janela
        pipes = new ArrayList<>();
        coins = new ArrayList<>();
        speedModifiers = new ArrayList<>();
        random = new Random();
        resetGame();
    }

    public Bird getBird() {
        return bird;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public List<SpeedModifier> getSpeedModifiers() {
        return speedModifiers;
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
        pipeSpeed = 1;
        coinSpeed = 1;
        speedModifierSpeed = 1;


        // inicializa os 10 primeiros canos
        for (int i = 0; i < PIPES_COUNT; i++) {
            addPipe(WIDTH / 2 + i * (PIPE_WIDTH + PIPE_DISTANCE));
        }
    }

    private void addPipe(int x) {
        int gapStart = MARGIN + random.nextInt(HEIGHT - PIPE_GAP - 2 * MARGIN);
        pipes.add(new Pipe(x, gapStart, PIPE_GAP));

        // 50% de chance de adicionar uma coin ou um speedModifier
        int startY = gapStart + PIPE_GAP / 2; // posição y no meio do gap
        if (random.nextBoolean()) {
            coins.add(new Coin(x + PIPE_WIDTH + PIPE_DISTANCE / 2 - 1, startY)); // x centraliza a coin entre pipes
        } else {
            speedModifiers.add(new SpeedModifier(x + PIPE_WIDTH + PIPE_DISTANCE / 2 - 1, startY));
        }
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

        // checa se o modificador de velocidade expirou
        if (speedModifierEndTime > 0 && System.currentTimeMillis() > speedModifierEndTime) {
            pipeSpeed = 1;
            speedModifierSpeed = 1;
            coinSpeed = 1;
            speedModifierEndTime = 0;
        }

        // move os canos para a esquerda
        for (Pipe pipe : pipes) {
            pipe.moveLeft(pipeSpeed);
        }

        // move as coins em Y e para esquerda
        for (Coin coin : coins) {
            coin.moveLeft(coinSpeed);
            coin.moveY(HEIGHT);
        }

        // move os speedModifiers em Y e para esquerda
        for (SpeedModifier speedModifier : speedModifiers) {
            speedModifier.moveLeft(speedModifierSpeed);
            speedModifier.moveY(HEIGHT);
        }

        // verifica se o bird passou o cano
        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && (WIDTH / 4) > (pipe.getX() + PIPE_WIDTH)) { // passou
                pipe.setScored(true);
                score+=PIPE_SCORE;
            }
        }

        // remove canos que estão fora da tela e adiciona novos
        if (!pipes.isEmpty() && pipes.getFirst().isOutOfScreen()) {
            pipes.removeFirst();
            addNewPipe();
        }

        // remove coins que estão fora da tela
        if (!coins.isEmpty() && coins.getFirst().isOutOfScreen()) {
            coins.removeFirst();
        }

        // remove speedModifiers que estão fora da tela
        if (!speedModifiers.isEmpty() && speedModifiers.getFirst().isOutOfScreen()) {
            speedModifiers.removeFirst();
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

        // colisão do bird com coins
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (birdX >= coin.getX() && birdX < coin.getX() + COIN_WIDTH &&
                    birdY >= coin.getY() && birdY < coin.getY() + COIN_WIDTH) {
                coins.remove(i);
                score += COIN_SCORE;
                break;
            }
        }

        // colisão do bird com speedModifiers
        for (int i = 0; i < speedModifiers.size(); i++) {
            SpeedModifier speedModifier = speedModifiers.get(i);
            if (birdX >= speedModifier.getX() && birdX < speedModifier.getX() + SPEED_MODIFIER_WIDTH &&
                    birdY >= speedModifier.getY() && birdY < speedModifier.getY() + SPEED_MODIFIER_HEIGHT) {
                speedModifiers.remove(i);
                pipeSpeed = 2;
                speedModifierSpeed = 2;
                coinSpeed = 2;
                speedModifierEndTime = System.currentTimeMillis() + SPEED_MODIFIER_DURATION;
                break;
            }
        }
    }

    public void jumpBird() {
        started = true;
        bird.jump(JUMP_HEIGHT);
    }
}