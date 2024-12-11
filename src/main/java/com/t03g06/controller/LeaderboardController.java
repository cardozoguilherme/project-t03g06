package com.t03g06.controller;

import com.t03g06.Game;
import com.t03g06.model.Leaderboard;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.model.MenuModel;
import com.t03g06.states.MenuState;

import java.io.IOException;

public class LeaderboardController implements Controller {
    private final Leaderboard leaderboard;

    public LeaderboardController(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public void step(Game game) {
        try {
            // Captura a entrada do teclado
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                // Se pressionado Enter, exibe o leaderboard e reseta
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    // Ao pressionar Escape, você pode retornar ao menu
                    System.out.println("Voltando ao menu...");
                    // Aqui você pode mudar o estado para o menu
                    game.setState(new MenuState(new MenuModel(), game.getTextGraphics()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
