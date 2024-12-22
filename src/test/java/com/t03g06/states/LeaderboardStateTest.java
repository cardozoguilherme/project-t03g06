package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.menu.LeaderboardController;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.states.LeaderboardState;
import com.t03g06.view.menu.LeaderboardViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class LeaderboardStateTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private Leaderboard model;
    private LeaderboardState state;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        model = mock(Leaderboard.class);

        state = new LeaderboardState(model, tg);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(LeaderboardViewer.class, state.getViewer());
    }

    @Test
    void testGetController() {
        assertInstanceOf(LeaderboardController.class, state.getController());
    }

    @Test
    void testStep() throws IOException {
        LeaderboardViewer viewer = mock(LeaderboardViewer.class);
        LeaderboardController controller = mock(LeaderboardController.class);

        LeaderboardState stateMock = new LeaderboardState(model, tg) {
            @Override
            public LeaderboardViewer getViewer() {
                return viewer;
            }

            @Override
            public LeaderboardController getController() {
                return controller;
            }
        };

        stateMock.step(game, screen);

        verify(controller).processInput(game);
        verify(viewer).draw();
        verify(screen).refresh();
    }
}
