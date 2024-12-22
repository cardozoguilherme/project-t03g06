package com.t03g06.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;

import com.t03g06.controller.GameOverController;
import com.t03g06.states.GameState;
import com.t03g06.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverControllerTest {
    private Game game;
    private GameOverController controller;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        controller = new GameOverController();
    }

    @Test
    void testRestartKeyLowercase() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('r');
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testRestartKeyUppercase() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('R');
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testReturnToMenu() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Escape);
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(game).setState(any(MenuState.class));
    }

    @Test
    void testNullKey() throws IOException {
        when(game.getInput()).thenReturn(null);

        controller.processInput(game);

        verify(game, never()).setState(any());
    }

    @Test
    void testIOException() throws IOException {
        when(game.getInput()).thenThrow(IOException.class);

        controller.processInput(game);

        verify(game, never()).setState(any());
    }
}
