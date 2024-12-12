package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.view.menu.LeaderboardViewer;
import com.t03g06.view.Viewer;import com.t03g06.controller.menu.LeaderboardController;

public class LeaderboardState extends State<Leaderboard> {

    public LeaderboardState(Leaderboard model, TextGraphics tg) {
        super(model,tg);//Passa o modelo Leaderboard e TextGraphics
    }

    @Override
    protected Viewer getViewer() {
        return new LeaderboardViewer(getModel(), getTextGraphics());  // Exibe o Leaderboard
    }

    @Override
    protected Controller getController() {
            return new LeaderboardController(getModel());
          // Retorna o controlador para este estado
    }
}
