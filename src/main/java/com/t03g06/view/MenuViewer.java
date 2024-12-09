package com.t03g06.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.MenuModel;

public class MenuViewer implements Viewer<MenuModel> {
    private final MenuModel model;
    private final TextGraphics tg;

    public MenuViewer(MenuModel model, TextGraphics tg) {
        this.model = model;
        this.tg = tg;
    }

    @Override
    public void draw() {
        // cor de fundo e do caractere
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        // preenche a tela com o caractere de fundo
        tg.fill(' ');

        // título do jogo
        tg.putString(35, 5, "FLAPPY BIRD");

        // desenha as opções do menu
        int optionY = 10;
        for (int i = 0; i < model.getOptions().size(); i++) {
            if (i == model.getCurrentOption()) {
                tg.setForegroundColor(TextColor.ANSI.YELLOW); // marca a opção selecionada
            } else {
                tg.setForegroundColor(TextColor.ANSI.WHITE);
            }
            tg.putString(35, optionY, model.getOptions().get(i));
            optionY += 2; // distância entre as opções;
        }
    }
}
