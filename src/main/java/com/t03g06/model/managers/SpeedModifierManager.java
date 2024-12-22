package com.t03g06.model.managers;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.SpeedModifier;

import java.util.ArrayList;
import java.util.List;

public class SpeedModifierManager {
    private final List<SpeedModifier> speedModifiers = new ArrayList<>();

    public void reset() {
        speedModifiers.clear(); // esvazia a lista
    }

    public void addModifier(int x, int y) {
        speedModifiers.add(new SpeedModifier(x, y));
    }

    public void update(int speedModifierSpeed) {
        // move speedModifiers em Y e para esquerda
        for (SpeedModifier sm : speedModifiers) {
            sm.moveLeft(speedModifierSpeed);
            sm.moveY(GameConstants.HEIGHT);
        }

        // remove speedModifiers que estão fora da tela
        speedModifiers.removeIf(SpeedModifier::isOutOfScreen);
    }

    public List<SpeedModifier> getSpeedModifiers() {
        return speedModifiers;
    }
}
