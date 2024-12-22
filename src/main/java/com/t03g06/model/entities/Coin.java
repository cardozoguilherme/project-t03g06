package com.t03g06.model.entities;

import com.t03g06.model.GameConstants;

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
            if (y < maxY - GameConstants.COIN_HEIGHT - GameConstants.COIN_MARGIN) {
                y++;
            } else {
                movingDown = false;
            }
        } else {
            if (y > GameConstants.COIN_MARGIN) {
                y--;
            } else {
                movingDown = true;
            }
        }
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isOutOfScreen() {
        return x + GameConstants.COIN_WIDTH < 0;
    }
}
