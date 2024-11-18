package com.t03g06.model;

public class Bird {
    private int y; // posição do bird
    private final int initialY; // posição inicial de bird

    public Bird(int y, int initialY) {
        this.y = y;
        this.initialY = initialY;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reset() {
        this.y = initialY;
    }

    public void jump() {
        this.y -= 3;
    }

    public void gravity() {
        this.y ++;
    }
}
