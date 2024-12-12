package com.t03g06.model.elements;

public class Bird {
    private int y; // posição vertical do bird
    private final int initialY; // posição inicial do bird

    public Bird(int initialY) {
        this.y = initialY;
        this.initialY = initialY;
    }

    public void setY(int y){ this.y = y; }

    public int getY() {
        return y;
    }

    public void reset() {
        this.y = initialY;
    }

    public void jump(int jumpHeight) {
        this.y -= jumpHeight; // movimento para cima
    }

    public void applyGravity(int gravity) {
        this.y+=gravity; // movimento para baixo
    }

}