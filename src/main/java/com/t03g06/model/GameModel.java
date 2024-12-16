package com.t03g06.model;

import com.t03g06.model.elements.Bird;
import com.t03g06.model.elements.Coin;
import com.t03g06.model.elements.Pipe;
import com.t03g06.model.elements.SpeedModifier;
import com.t03g06.model.menu.Leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
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

    private final Leaderboard leaderboard; // instância do leaderboard

    public GameModel() {
        bird = new Bird(GameConstants.HEIGHT / 2); // altura do bird é metade da altura da janela
        pipes = new ArrayList<>();
        coins = new ArrayList<>();
        speedModifiers = new ArrayList<>();
        random = new Random();
        leaderboard = new Leaderboard();
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
        for (int i = 0; i < GameConstants.PIPES_COUNT; i++) {
            addPipe(GameConstants.WIDTH / 2 + i * (GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE));
        }
    }

    private void gameOver() {
        gameOver = true;
        leaderboard.addScore(score);
        leaderboard.saveScores();
    }

    private void addPipe(int x) {
        int gapStart = GameConstants.MARGIN + random.nextInt(GameConstants.HEIGHT - GameConstants.PIPE_GAP - 2 * GameConstants.MARGIN);
        pipes.add(new Pipe(x, gapStart, GameConstants.PIPE_GAP));

        // 50% de chance de adicionar uma coin ou um speedModifier
        int startY = gapStart + GameConstants.PIPE_GAP / 2; // posição y no meio do gap
        if (random.nextBoolean()) {
            coins.add(new Coin(x + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE / 2 - 1, startY)); // x centraliza a coin entre pipes
        } else {
            speedModifiers.add(new SpeedModifier(x + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE / 2 - 1, startY));
        }
    }

    public void addNewPipe() {
        if (!pipes.isEmpty()) {
            int lastPipeX = pipes.getLast().getX();
            addPipe(lastPipeX + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE);
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
            coin.moveY(GameConstants.HEIGHT);
        }

        // move os speedModifiers em Y e para esquerda
        for (SpeedModifier speedModifier : speedModifiers) {
            speedModifier.moveLeft(speedModifierSpeed);
            speedModifier.moveY(GameConstants.HEIGHT);
        }

        // verifica se o bird passou o cano
        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && (GameConstants.WIDTH / 4) > (pipe.getX() + GameConstants.PIPE_WIDTH)) { // passou
                pipe.setScored(true);
                score+=GameConstants.PIPE_SCORE;
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

        bird.applyGravity(GameConstants.GRAVITY);
        checkCollision();
    }

    private void checkCollision() {
        int birdX = GameConstants.WIDTH / 4;
        int birdY = bird.getY();

        // checa colisão do bird com pipe
        for (Pipe pipe : pipes) {
            if (birdX >= pipe.getX() && birdX <= pipe.getX() + GameConstants.PIPE_WIDTH) {
                if (birdY < pipe.getGapStart() || birdY >= pipe.getGapStart() + pipe.getGapSize()) {
                    gameOver();
                    return;
                }
            }
        }

        // checa colisão do bird com o chão ou teto
        if (birdY < 0 || birdY >= GameConstants.HEIGHT) {
            gameOver();
        }

        // colisão do bird com coins
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (birdX >= coin.getX() && birdX < coin.getX() + GameConstants.COIN_WIDTH &&
                    birdY >= coin.getY() && birdY < coin.getY() + GameConstants.COIN_WIDTH) {
                coins.remove(i);
                score += GameConstants.COIN_SCORE;
                break;
            }
        }

        // colisão do bird com speedModifiers
        for (int i = 0; i < speedModifiers.size(); i++) {
            SpeedModifier speedModifier = speedModifiers.get(i);
            if (birdX >= speedModifier.getX() && birdX < speedModifier.getX() + GameConstants.SPEED_MODIFIER_WIDTH &&
                    birdY >= speedModifier.getY() && birdY < speedModifier.getY() + GameConstants.SPEED_MODIFIER_HEIGHT) {
                speedModifiers.remove(i);
                pipeSpeed = 2;
                speedModifierSpeed = 2;
                coinSpeed = 2;
                speedModifierEndTime = System.currentTimeMillis() + GameConstants.SPEED_MODIFIER_DURATION;
                break;
            }
        }
    }

    public void jumpBird() {
        started = true;
        bird.jump(GameConstants.JUMP_HEIGHT);
    }
}