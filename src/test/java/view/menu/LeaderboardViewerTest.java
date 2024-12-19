package view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.menu.Leaderboard;
import com.t03g06.view.menu.LeaderboardViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class LeaderboardViewerTest {
    private TextGraphics tg;
    private Leaderboard model;
    private LeaderboardViewer viewer;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);

        model = mock(Leaderboard.class);
        when(model.getTopScores()).thenReturn(Arrays.asList(100, 90, 80, 70, 60));

        viewer = new LeaderboardViewer(model, tg);
    }

    @Test
    void testDraw() {
        viewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.BLACK);
        verify(tg).fill(' ');

        verify(tg).putString(35, 2, "LEADERBOARD");

        verify(tg, atLeastOnce()).setForegroundColor(TextColor.ANSI.WHITE);
        int y = 5;
        for (int score : model.getTopScores()) {
            verify(tg).putString(35, y, String.valueOf(score));
            y += 1;
        }

        verify(tg, atLeastOnce()).setForegroundColor(TextColor.ANSI.YELLOW);
        verify(tg).putString(25, y + 2, "Press ESC to return to the menu.");
    }
}
