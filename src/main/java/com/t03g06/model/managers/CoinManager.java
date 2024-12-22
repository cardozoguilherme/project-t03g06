package com.t03g06.model.managers;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinManager {
    private final List<Coin> coins = new ArrayList<>();

    public void reset() {
        coins.clear(); // esvazia a lista
    }

    public void update(int coinSpeed) {
        // move coins em Y e para esquerda
        for (Coin coin : coins) {
            coin.moveLeft(coinSpeed);
            coin.moveY(GameConstants.HEIGHT);
        }

        // remove coins que estão fora da tela
        coins.removeIf(Coin::isOutOfScreen);
    }

    public void addCoin(int x, int y) {
        coins.add(new Coin(x, y));
    }

    public List<Coin> getCoins() {
        return coins;
    }
}

