package com.t03g06.model;

public class Bird {
    private int y; // posição vertical do pássaro
    private final int initialY; // posição inicial do pássaro

    public Bird(int initialY) {
        this.y = initialY;
        this.initialY = initialY;
    }

    public int getY() {
        return y;
    }

    public void reset() {
        this.y = initialY;
    }

    public void jump() {
        this.y -= 3; // movimento para cima
    }

    public void applyGravity() {
        this.y++; // movimento para baixo
    }
}