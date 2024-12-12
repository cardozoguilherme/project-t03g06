package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.controller.Controller;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.view.menu.LeaderboardViewer;
import com.t03g06.view.Viewer;
import com.t03g06.controller.menu.LeaderboardController;

public class LeaderboardState extends State<Leaderboard> {

    public LeaderboardState(Leaderboard model, TextGraphics tg) {
        super(model,tg);
    }

    @Override
    protected Viewer getViewer() {
        return new LeaderboardViewer(getModel(), getTextGraphics());
    }

    @Override
    protected Controller getController() {
            return new LeaderboardController();
    }
}
