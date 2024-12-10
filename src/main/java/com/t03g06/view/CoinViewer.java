package com.t03g06.view;

import com.t03g06.model.Coin;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class CoinViewer {
    private final List<Coin> coins;
    private final TextGraphics tg;

    public CoinViewer(List<Coin> coins, TextGraphics tg) {
        this.coins = coins;
        this.tg = tg;
    }

    public void draw() {
        tg.setBackgroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        for (Coin coin : coins) {
            // desenha um bloco 2x2
            tg.putString(coin.getX(), coin.getY(), "  ");
            tg.putString(coin.getX(), coin.getY() + 1, "  ");
        }
        // volta à cor padrão do fundo
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
    }
}
