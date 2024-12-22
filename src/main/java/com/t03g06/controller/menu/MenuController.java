package com.t03g06.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.controller.Controller;
import com.t03g06.model.GameModel;
import com.t03g06.model.menu.HowToPlayModel;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.GameState;
import com.t03g06.states.HowToPlayState;
import com.t03g06.states.LeaderboardState;

import java.io.IOException;

public class MenuController implements Controller {
    private final MenuModel model;

    public MenuController(MenuModel model) {
        this.model = model;
    }

    @Override
    public void processInput(Game game) {
        try {
            // captura a entrada do teclado
            KeyStroke keyStroke = game.getInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                    model.nextOption(); // move para a próxima opção
                } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                    model.previousOption(); // move para a opção anterior
                } else if (keyStroke.getKeyType() == KeyType.Enter) {
                    switch (model.getCurrentOption()) {
                        case 0 -> game.setState(new GameState(new GameModel(), game.getTextGraphics())); // inicia o jogo com GameModel
                        case 1 -> game.setState(new HowToPlayState(new HowToPlayModel(), game.getTextGraphics()));
                        case 2 -> game.setState(new LeaderboardState(new Leaderboard(), game.getTextGraphics()));
                        case 3 -> System.exit(0); // sai do jogo
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while processing input: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
