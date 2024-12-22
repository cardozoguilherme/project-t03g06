package states;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t03g06.Game;
import com.t03g06.controller.menu.MenuController;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.MenuState;
import com.t03g06.view.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class MenuStateTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private MenuModel model;
    private MenuState state;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        model = mock(MenuModel.class);

        state = new MenuState(model, tg);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(MenuViewer.class, state.getViewer());
    }

    @Test
    void testGetController() {
        assertInstanceOf(MenuController.class, state.getController());
    }

    @Test
    void testStep() throws IOException {
        MenuViewer viewer = mock(MenuViewer.class);
        MenuController controller = mock(MenuController.class);

        MenuState stateMock = new MenuState(model, tg) {
            @Override
            public MenuViewer getViewer() {
                return viewer;
            }

            @Override
            public MenuController getController() {
                return controller;
            }
        };

        stateMock.step(game, screen);

        verify(controller).processInput(game);
        verify(viewer).draw();
        verify(screen).refresh();
    }
}
