package com.t03g06.view.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.view.Viewer;

public class LeaderboardViewer implements Viewer {
    private final Leaderboard model;
    private final TextGraphics tg;

    public LeaderboardViewer(Leaderboard model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    @Override
    public void draw() {
        // cor de fundo e do texto
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        // preenche a tela com o caractere de fundo
        tg.fill(' ');

        // título do leaderboard
        tg.setForegroundColor(TextColor.ANSI.YELLOW);
        tg.putString(35, 2, "LEADERBOARD");

        // desenha as pontuações
        int y = 5;  // posição inicial para desenhar as pontuações
        for (int score : model.getTopScores()) {
            tg.setForegroundColor(TextColor.ANSI.WHITE);
            tg.putString(35, y, String.valueOf(score));
            y += 1;
        }

        tg.setForegroundColor(TextColor.ANSI.YELLOW);
        tg.putString(25, y+=2, "Press ESC to return to the menu.");
    }
}
