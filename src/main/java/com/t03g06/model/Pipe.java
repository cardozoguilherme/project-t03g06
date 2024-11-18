package com.t03g06.model;

public class Pipe {
    private int x; // posição na horizontal
    private final int gapStart; // inicio do espaço entre os canos em y
    private final int gapSize; // tamanho do espaçamento entre os canos

    /*
        Ex.:
        Pipe pipe = new Pipe(80, 10, 12);
        O pipe vai ser posicionado em x = 80
        Terá um gap da linha 10 até a linha 22 (10 + 12)
     */
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

    // movimenta o pipe para a esquerda
    public void moveLeft() {
        this.x --;
    }
}
