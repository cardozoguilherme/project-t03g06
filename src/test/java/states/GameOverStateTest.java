package states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.GameOverController;
import com.t03g06.model.GameModel;
import com.t03g06.states.GameOverState;
import com.t03g06.view.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class GameOverStateTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private GameModel model;
    private GameOverState state;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        model = mock(GameModel.class);

        state = new GameOverState(model, tg);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(GameOverViewer.class, state.getViewer());
    }

    @Test
    void testGetController() {
        assertInstanceOf(GameOverController.class, state.getController());
    }

    @Test
    void testStep() throws IOException {
        GameOverViewer viewer = mock(GameOverViewer.class);
        GameOverController controller = mock(GameOverController.class);

        GameOverState stateMock = new GameOverState(model, tg) {
            @Override
            public GameOverViewer getViewer() {
                return viewer;
            }

            @Override
            public GameOverController getController() {
                return controller;
            }
        };

        stateMock.step(game, screen);

        verify(controller).processInput(game);
        verify(viewer).draw();
        verify(screen).refresh();
    }
}
