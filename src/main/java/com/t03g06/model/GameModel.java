package com.t03g06.model;

import com.t03g06.model.elements.Bird;
import com.t03g06.model.managers.CollisionManager;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import com.t03g06.model.menu.Leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private final Bird bird;
    private final CoinManager coinManager;
    private final SpeedModifierManager speedModifierManager;
    private final PipeManager pipeManager;
    private final CollisionManager collisionManager;
    private final Leaderboard leaderboard; // instância do leaderboard

    private int score;
    private boolean gameOver = false;
    private boolean started = false;
    private int coinSpeed = 1;
    private int speedModifierSpeed = 1;
    private int pipeSpeed = 1;
    private long speedModifierEndTime = 0; // tempo em que o modificador expira

    public GameModel() {
        this.bird = new Bird(GameConstants.HEIGHT / 2); // altura do bird é metade da altura da janela
        this.coinManager = new CoinManager();
        this.speedModifierManager = new SpeedModifierManager();
        this.pipeManager = new PipeManager(coinManager, speedModifierManager);
        this.collisionManager = new CollisionManager();
        this.leaderboard = new Leaderboard();
        resetGame();
    }

    public Bird getBird() {
        return bird;
    }

    public PipeManager getPipeManager() {
        return pipeManager;
    }

    public CoinManager getCoinManager() {
        return coinManager;
    }

    public SpeedModifierManager getSpeedModifierManager() {
        return speedModifierManager;
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
        pipeSpeed = 1;
        coinSpeed = 1;
        speedModifierSpeed = 1;
        coinManager.reset();
        speedModifierManager.reset();
        pipeManager.reset();
    }

    private void gameOver() {
        gameOver = true;
        leaderboard.addScore(score);
        leaderboard.saveScores();
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
        // move os canos, remove os que estão fora da tela e adiciona novos
        // a lógica de spawn de coins e speed modifiers é no update de pipeManager
        pipeManager.update(pipeSpeed);
        // move coins em Y e para esquerda, remove os que estão fora da tela
        coinManager.update(coinSpeed);
        // move speedModifiers em Y e para esquerda, remove os que estão fora da tela
        speedModifierManager.update(speedModifierSpeed);

        // atualiza o score quando se o bird passou o pipe
        score += pipeManager.updateScore();

        bird.applyGravity(GameConstants.GRAVITY);

        checkCollisions();
    }

    private void checkCollisions() {
        // se bird colidiu com pipe, teto ou chão, então é game over
        if (collisionManager.checkPipeCollision(bird, pipeManager.getPipes()) ||
                collisionManager.checkBoundaryCollision(bird)) {
            gameOver();
        }

        // se bird colidiu com coin, o score é incrementado
        if (collisionManager.checkCoinCollision(bird, coinManager.getCoins())) {
            score += GameConstants.COIN_SCORE;
        }

        // se bird colidiu com speedModifier, a velocidade do jogo aumenta por um tempo
        if (collisionManager.checkSpeedModifierCollision(bird, speedModifierManager.getSpeedModifiers())) {
            pipeSpeed = 2;
            coinSpeed = 2;
            speedModifierSpeed = 2;
            speedModifierEndTime = System.currentTimeMillis() + GameConstants.SPEED_MODIFIER_DURATION;
        }
    }

    public void jumpBird() {
        started = true;
        bird.jump(GameConstants.JUMP_HEIGHT);
    }
}