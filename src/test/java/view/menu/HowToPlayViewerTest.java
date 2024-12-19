package view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.menu.HowToPlayModel;
import com.t03g06.view.menu.HowToPlayViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class HowToPlayViewerTest {
    private TextGraphics tg;
    private HowToPlayModel model;
    private HowToPlayViewer viewer;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);
        model = new HowToPlayModel();
        viewer = new HowToPlayViewer(model, tg);
    }

    @Test
    void testDraw() {
        viewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.BLACK);
        verify(tg).setForegroundColor(TextColor.ANSI.WHITE);
        verify(tg).fill(' ');

        verify(tg).putString(35, 2, "HOW TO PLAY");

        int y = 5;
        for (String instruction : model.getInstructions()) {
            verify(tg).putString(15, y, instruction);
            y += 2;
        }

        verify(tg).setForegroundColor(TextColor.ANSI.YELLOW);
        verify(tg).putString(20, y + 2, "Press ESC to return to the menu.");
    }
}
