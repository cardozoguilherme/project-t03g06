package com.t03g06.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.t03g06.model.Leaderboard;

public class LeaderboardViewer implements Viewer<Leaderboard> {
    private final Leaderboard model;
    private final TextGraphics tg;

    public LeaderboardViewer(Leaderboard model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    @Override
    public void draw() {
        // Cor de fundo e do texto
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        // Preenche a tela com o caractere de fundo
        tg.fill(' ');

        // Título do leaderboard
        tg.setForegroundColor(TextColor.ANSI.YELLOW);
        tg.putString(35, 2, "LEADERBOARD");

        // Desenha os jogadores e suas pontuações
        int y = 5;  // Posição inicial para desenhar as pontuações
        for (var entry : model.getScores()) {
            tg.setForegroundColor(TextColor.ANSI.WHITE);
            tg.putString(35, y, entry.getKey() + ": " + entry.getValue());
            y += 1;
        }

    }
}
