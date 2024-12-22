package com.t03g06.model.entities;

import com.t03g06.model.GameConstants;

public class SpeedModifier {
    private int x;
    private int y;
    private boolean movingDown = true;

    public SpeedModifier(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft(int speedModifierSpeed) {
        this.x-=speedModifierSpeed;
    }

    public void moveY(int maxY) {
        if (movingDown) {
            if (y < maxY - GameConstants.SPEED_MODIFIER_HEIGHT - GameConstants.SPEED_MODIFIER_MARGIN) {
                y++;
            } else {
                movingDown = false;
            }
        } else {
            if (y > GameConstants.SPEED_MODIFIER_HEIGHT) {
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
        return x + GameConstants.SPEED_MODIFIER_WIDTH < 0;
    }
}
