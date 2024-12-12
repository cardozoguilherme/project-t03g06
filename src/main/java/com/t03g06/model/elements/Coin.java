package com.t03g06.model.elements;

import com.t03g06.model.GameModel;

public class Coin {
    private int x;
    private int y;
    private boolean movingDown = true;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft(int coinSpeed) {
        this.x-=coinSpeed;
    }

    public void moveY(int maxY) {
        if (movingDown) {
            if (y < maxY - GameModel.COIN_HEIGHT - GameModel.COIN_MARGIN) {
                y++;
            } else {
                movingDown = false;
            }
        } else {
            if (y > GameModel.COIN_MARGIN) {
                y--;
            } else {
                movingDown = true;
            }
        }
    }

    public boolean isOutOfScreen() {
        return x + GameModel.COIN_WIDTH < 0;
    }
}
