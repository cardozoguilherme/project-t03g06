package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.GameController;
import com.t03g06.model.GameModel;
import com.t03g06.model.entities.Coin;
import com.t03g06.model.entities.Pipe;
import com.t03g06.model.entities.SpeedModifier;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import com.t03g06.states.GameState;
import com.t03g06.view.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class GameStateTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private GameModel model;
    private GameState state;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        model = mock(GameModel.class);

        state = new GameState(model, tg);
    }

    @Test
    void testGetViewer() {
        CoinManager coinManager = mock(CoinManager.class);
        PipeManager pipeManager = mock(PipeManager.class);
        SpeedModifierManager speedModifierManager = mock(SpeedModifierManager.class);

        when(model.getCoinManager()).thenReturn(coinManager);
        when(model.getPipeManager()).thenReturn(pipeManager);
        when(model.getSpeedModifierManager()).thenReturn(speedModifierManager);

        when(coinManager.getCoins()).thenReturn(Arrays.asList(mock(Coin.class), mock(Coin.class)));
        when(pipeManager.getPipes()).thenReturn(Arrays.asList(mock(Pipe.class), mock(Pipe.class)));
        when(speedModifierManager.getSpeedModifiers()).thenReturn(Arrays.asList(mock(SpeedModifier.class), mock(SpeedModifier.class)));

        GameViewer viewer = (GameViewer) state.getViewer();
        assertInstanceOf(GameViewer.class, viewer);
    }

    @Test
    void testGetController() {
        assertInstanceOf(GameController.class, state.getController());
    }

    @Test
    void testStep() throws IOException {
        GameViewer viewer = mock(GameViewer.class);
        GameController controller = mock(GameController.class);

        GameState stateMock = new GameState(model, tg) {
            @Override
            public GameViewer getViewer() {
                return viewer;
            }

            @Override
            public GameController getController() {
                return controller;
            }
        };

        stateMock.step(game, screen);

        verify(controller).processInput(game);
        verify(viewer).draw();
        verify(screen).refresh();
    }
}
