package com.t03g06.model.managers;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Bird;
import com.t03g06.model.entities.Coin;
import com.t03g06.model.entities.Pipe;
import com.t03g06.model.entities.SpeedModifier;

import java.util.List;

public class CollisionManager {
    // checa colisão do bird com pipe
    public boolean checkPipeCollision(Bird bird, List<Pipe> pipes) {
        int birdX = GameConstants.WIDTH / 4;
        int birdY = bird.getY();

        for (Pipe pipe : pipes) {
            if (birdX >= pipe.getX() && birdX <= pipe.getX() + GameConstants.PIPE_WIDTH) {
                if (birdY < pipe.getGapStart() || birdY >= pipe.getGapStart() + pipe.getGapSize()) {
                    return true; // colidiu com pipe
                }
            }
        }
        return false;
    }

    // colisão do bird com coins
    public boolean checkCoinCollision(Bird bird, List<Coin> coins) {
        int birdX = GameConstants.WIDTH / 4;
        int birdY = bird.getY();

        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (birdX >= coin.getX() && birdX < coin.getX() + GameConstants.COIN_WIDTH &&
                    birdY >= coin.getY() && birdY < coin.getY() + GameConstants.COIN_HEIGHT) {
                coins.remove(i);
                return true; // colidiu com coin
            }
        }
        return false;
    }

    // colisão do bird com speedModifiers
    public boolean checkSpeedModifierCollision(Bird bird, List<SpeedModifier> speedModifiers) {
        int birdX = GameConstants.WIDTH / 4;
        int birdY = bird.getY();

        for (int i = 0; i < speedModifiers.size(); i++) {
            SpeedModifier sm = speedModifiers.get(i);
            if (birdX >= sm.getX() && birdX < sm.getX() + GameConstants.SPEED_MODIFIER_WIDTH &&
                    birdY >= sm.getY() && birdY < sm.getY() + GameConstants.SPEED_MODIFIER_HEIGHT) {
                speedModifiers.remove(i);
                return true; // colidiu com SpeedModifier
            }
        }
        return false;
    }

    // checa colisão do bird com o chão ou teto
    public boolean checkBoundaryCollision(Bird bird) {
        int birdY = bird.getY();
        return birdY < 0 || birdY >= GameConstants.HEIGHT;
    }
}
