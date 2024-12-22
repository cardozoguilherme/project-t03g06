package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.t03g06.Game;
import com.t03g06.controller.GameController;
import com.t03g06.model.GameModel;
import com.t03g06.states.GameOverState;
import com.t03g06.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameControllerTest {
    private Game game;
    private GameModel model;
    private GameController controller;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        model = mock(GameModel.class);
        controller = new GameController(model);
    }

    @Test
    void testGameOver() {
        when(model.isGameOver()).thenReturn(true);

        controller.processInput(game);

        verify(game).setState(any(GameOverState.class));
        verify(model, never()).updateGame();
    }

    @Test
    void testProcessInputSpaceKey() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn(' ');
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(model).jumpBird();
        verify(model).updateGame();
    }

    @Test
    void testRestartKeyLowerCase() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('r');
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testRestartKeyUppperCase() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('R');
        when(game.getInput()).thenReturn(keyStroke);

        controller.processInput(game);

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testNullKey() throws IOException {
        when(game.getInput()).thenReturn(null);

        controller.processInput(game);

        verify(model).updateGame();
    }

    @Test
    void testIOException() throws IOException {
        when(game.getInput()).thenThrow(new IOException());

        controller.processInput(game);

        verify(model).updateGame();
    }
}
