package com.t03g06.model;

public class Pipe {
    private int x; // posição horizontal do cano
    private final int gapStart; // início do espaçamento vertical dos canos
    private final int gapSize; // tamanho do espaço vertical
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

    public void moveLeft(int pipeSpeed) {
        this.x-=pipeSpeed;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public boolean isOutOfScreen() {
        return x + GameModel.PIPE_WIDTH < 0; // verifica se o cano saiu da tela
    }
}

