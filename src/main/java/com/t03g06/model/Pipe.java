package com.t03g06.model;

import static com.t03g06.model.GameState.PIPE_WIDTH;

public class Pipe {
    private int x; // posição horizontal do cano
    private final int gapStart; // início do e
    private final int gapSize; // tamanho do gap
    private boolean scored = false;

    public Pipe(int x, int gapStart, int gapSize) {
        this.x = x;
        this.gapStart = gapStart;
        this.gapSize = gapSize;
    }

    public int getX() {
        return x;
    }

    public int getGapStart() {
        return gapStart;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void moveLeft() {
        this.x--;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public boolean isOutOfScreen(int screenWidth) {
        return x + PIPE_WIDTH < 0; // verifica se o cano saiu da tela
    }
}

