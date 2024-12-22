package com.t03g06.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.view.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class MenuViewerTest {
    private TextGraphics tg;
    private MenuViewer viewer;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);

        MenuModel model = mock(MenuModel.class);
        when(model.getOptions()).thenReturn(Arrays.asList("PLAY", "HOW TO PLAY", "LEADERBOARD", "QUIT"));
        when(model.getCurrentOption()).thenReturn(1);

        viewer = new MenuViewer(model, tg);
    }

    @Test
    void testDraw() {
        viewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.BLACK);
        verify(tg).fill(' ');

        verify(tg).putString(35, 5, "FLAPPY BIRD");

        verify(tg, times(4)).setForegroundColor(TextColor.ANSI.WHITE);
        verify(tg).setForegroundColor(TextColor.ANSI.YELLOW);
    }
}
