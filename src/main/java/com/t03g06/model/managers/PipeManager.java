package com.t03g06.model.managers;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Pipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PipeManager {
    private final List<Pipe> pipes = new ArrayList<>();
    private final Random random = new Random();
    private final CoinManager coinManager;
    private final SpeedModifierManager speedModifierManager;

    public PipeManager(CoinManager coinManager, SpeedModifierManager speedModifierManager) {
        this.coinManager = coinManager;
        this.speedModifierManager = speedModifierManager;
    }

    public void reset() {
        pipes.clear(); // esvazia a lista

        // inicializa os 10 primeiros canos
        for (int i = 0; i < GameConstants.PIPES_COUNT; i++) {
            int x = GameConstants.WIDTH / 2 + i * (GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE);
            addPipe(x);
        }
    }

    public void update(int pipeSpeed) {
        // move os canos para a esquerda
        for (Pipe pipe : pipes) {
            pipe.moveLeft(pipeSpeed);
        }

        // remove canos que estão fora da tela e adiciona novos
        if (!pipes.isEmpty() && pipes.getFirst().isOutOfScreen()) {
            pipes.removeFirst();
            addNewPipe();
        }
    }

    public int updateScore() {
        int score = 0;
        // verifica se o bird passou o cano
        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && (GameConstants.WIDTH / 4) > (pipe.getX() + GameConstants.PIPE_WIDTH)) {
                pipe.setScored(true);
                score += GameConstants.PIPE_SCORE;
            }
        }
        return score;
    }

    private void addPipe(int x) {
        int gapStart = GameConstants.MARGIN + random.nextInt(GameConstants.HEIGHT - GameConstants.PIPE_GAP - 2 * GameConstants.MARGIN);
        pipes.add(new Pipe(x, gapStart, GameConstants.PIPE_GAP));

        // 50% de chance de adicionar uma coin ou um speedModifier
        int startY = gapStart + GameConstants.PIPE_GAP / 2; // posição y no meio do gap
        if (random.nextBoolean()) {
            coinManager.addCoin(x + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE / 2, startY);
        } else {
            speedModifierManager.addModifier(x + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE / 2, startY);
        }
    }

    private void addNewPipe() {
        int lastPipeX = pipes.getLast().getX();
        addPipe(lastPipeX + GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE);
    }

    public List<Pipe> getPipes() {
        return pipes;
    }
}
