package view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.view.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameOverViewerTest {
    private TextGraphics tg;
    private GameOverViewer viewer;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);
        viewer = new GameOverViewer(tg);
    }

    @Test
    void testDraw() {
        viewer.draw();

        verify(tg, times(1)).setBackgroundColor(TextColor.ANSI.RED);
        verify(tg, times(1)).setForegroundColor(TextColor.ANSI.WHITE);

        verify(tg).putString(35, 10, "GAME OVER");
        verify(tg).putString(30, 12, "Press 'R' to Restart");
        verify(tg).putString(25, 14, "Press 'Esc' to return to the menu");
    }
}
