package com.t03g06.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.menu.HowToPlayController;
import com.t03g06.model.menu.HowToPlayModel;
import com.t03g06.states.HowToPlayState;
import com.t03g06.view.menu.HowToPlayViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class HowToPlayStateTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private HowToPlayModel model;
    private HowToPlayState state;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        model = mock(HowToPlayModel.class);

        state = new HowToPlayState(model, tg);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(HowToPlayViewer.class, state.getViewer());
    }

    @Test
    void testGetController() {
        assertInstanceOf(HowToPlayController.class, state.getController());
    }

    @Test
    void testStep() throws IOException {
        HowToPlayViewer viewer = mock(HowToPlayViewer.class);
        HowToPlayController controller = mock(HowToPlayController.class);

        HowToPlayState stateMock = new HowToPlayState(model, tg) {
            @Override
            public HowToPlayViewer getViewer() {
                return viewer;
            }

            @Override
            public HowToPlayController getController() {
                return controller;
            }
        };

        stateMock.step(game, screen);

        verify(controller).processInput(game);
        verify(viewer).draw();
        verify(screen).refresh();
    }
}

