package controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.controller.menu.HowToPlayController;
import com.t03g06.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HowToPlayControllerTest {
    private HowToPlayController controller;
    private Game game;

    @BeforeEach
    void setUp() {
        controller = new HowToPlayController();
        game = mock(Game.class);
    }

    @Test
    void testEscapeKey() throws IOException {
        KeyStroke escapeKey = mock(KeyStroke.class);
        when(escapeKey.getKeyType()).thenReturn(KeyType.Escape);
        when(game.getInput()).thenReturn(escapeKey);

        controller.processInput(game);

        verify(game).setState(any(MenuState.class));
    }

    @Test
    void testNonEscapeKey() throws IOException {
        KeyStroke otherKey = mock(KeyStroke.class);
        when(otherKey.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(game.getInput()).thenReturn(otherKey);

        controller.processInput(game);

        verify(game, never()).setState(any(MenuState.class));
    }

    @Test
    void testNullKey() throws IOException {
        when(game.getInput()).thenReturn(null);

        controller.processInput(game);

        verify(game, never()).setState(any(MenuState.class));
    }

    @Test
    void testIOException() throws IOException {
        when(game.getInput()).thenThrow(new IOException());

        controller.processInput(game);

        verify(game, never()).setState(any(MenuState.class));
    }
}
