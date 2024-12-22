package com.t03g06.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.controller.menu.MenuController;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.GameState;
import com.t03g06.states.HowToPlayState;
import com.t03g06.states.LeaderboardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    private Game game;
    private MenuModel model;
    private MenuController controller;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        model = mock(MenuModel.class);
        controller = new MenuController(model);
    }

    @Test
    void testNextOption() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(model).nextOption();
    }

    @Test
    void testPreviousOption() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(model).previousOption();
    }

    @Test
    void testStartsGame() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        when(game.getInput()).thenReturn(keyStroke);
        when(model.getCurrentOption()).thenReturn(0);

        controller.processInput(game);

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testHowToPlay() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        when(game.getInput()).thenReturn(keyStroke);
        when(model.getCurrentOption()).thenReturn(1);

        controller.processInput(game);

        verify(game).setState(any(HowToPlayState.class));
    }

    @Test
    void testLeaderboard() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        when(game.getInput()).thenReturn(keyStroke);
        when(model.getCurrentOption()).thenReturn(2);

        controller.processInput(game);

        verify(game).setState(any(LeaderboardState.class));
    }

    @Test
    void testNullKey() throws IOException {
        when(game.getInput()).thenReturn(null);

        controller.processInput(game);

        verify(model, never()).nextOption();
        verify(model, never()).previousOption();
        verify(game, never()).setState(any());
    }

    @Test
    void testIOException() throws IOException {
        when(game.getInput()).thenThrow(new IOException());

        controller.processInput(game);

        verify(model, never()).nextOption();
        verify(model, never()).previousOption();
        verify(game, never()).setState(any());
    }
}
